package cuckoo;

import java.util.Random;

public class CuckooFilter implements Filter {

    private static final int BUCKET_SIZE = 1024; // 过滤器包含1024个bucket, 实际生产中该值为可配置
    private static final int ENTRY_SIZE = 4; // 每个bucket内包含4个entry, 为了让bucket中各entry可以一次内存读取，充分利用cpu缓存机制
    private static final Random random = new Random();

    private final Byte[][] buckets = new Byte[BUCKET_SIZE][ENTRY_SIZE];

    /**
     * 插入key方法
     */
    @Override
    public void put(String key) {
        byte fPrint = fingerprint(key); // 计算指纹与bucket的索引
        int index = bucketIndex(key);
        if (insert(index, fPrint)) // 尝试往bucket中插入指纹
            return;

        int backupIndex = backupBucketIndex(fPrint, index); // 插入失败则寻找备用bucket
        if (insert(backupIndex, fPrint)) // 再次尝试插入指纹
            return;

        // 插入失败则在2个bucket中随机选择一个，进行踢出后插入（即类布谷鸟行为）
        int kickOutIndex = (random.nextInt(2) == 0) ? index : backupIndex;
        insertAfterKickOut(kickOutIndex, fPrint);
    }

    /**
     * 查询key方法
     */
    @Override
    public boolean contains(String key) {
        byte fPrint = fingerprint(key);
        int index = bucketIndex(key);
        if (fingerprintExists(index, fPrint))
            return true;

        int backupIndex = backupBucketIndex(fPrint, index);
        return fingerprintExists(backupIndex, fPrint);
    }

    /**
     * 使用RSHash算法计算bucket的索引
     */
    private int bucketIndex(String key) {
        // 对hashCode取模(因为bucket总数1024=2^10，所以 a%1024 = a&(1024-1)，位运算快很多)
        return rsHash(key) & (BUCKET_SIZE - 1);
    }

    /**
     * 基于指纹和indexA计算备用bucket的索引
     */
    private int backupBucketIndex(Byte fPrint, int indexA) {
        return (rsHash(Byte.toString(fPrint)) ^ indexA) & (BUCKET_SIZE - 1);
    }

    /**
     * RSHash算法
     */
    private static int rsHash(String str) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return hash;
    }

    /**
     * 遍历bucket中entry，检查指纹是否存在
     */
    private boolean fingerprintExists(int index, byte fPrint) {
        Byte[] bucket = buckets[index];
        if (bucket != null) {
            for (Byte entry : bucket) {
                if (entry != null && entry == fPrint) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 遍历bucket中entry寻找空位插入指纹
     */
    private boolean insert(int index, byte fPrint) {
        Byte[] bucket = buckets[index];
        if (bucket == null) {
            bucket = new Byte[ENTRY_SIZE];
        }
        for (int i = 0; i < ENTRY_SIZE; i++) {
            if (bucket[i] == null) {
                bucket[i] = fPrint;
                return true;
            }
        }
        return false;
    }

    /**
     * 被踢出的指纹寻找备用bucket插入
     */
    private void insertAfterKickOut(int index, byte fPrint) {
        Byte[] bucket = buckets[index];
        int rand = random.nextInt(ENTRY_SIZE); // 随机踢出一个指纹
        Byte fPrintOut = bucket[rand];
        bucket[rand] = fPrint; // 当前指纹存入被踢出的位置

        // 被踢出的指纹寻找备用bucket重新插入
        int backupIndex = backupBucketIndex(fPrint, index);
        insert(backupIndex, fPrintOut);
    }

    /**
     * 取key的指纹
     */
    private byte fingerprint(String key) {
        return (byte) (rsHash(key) & 0xFF); // 取hashCode后8位
    }
}
