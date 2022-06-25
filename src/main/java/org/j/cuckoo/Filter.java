package cuckoo;

public interface Filter {

    void put(String key);

    boolean contains(String key);

}
