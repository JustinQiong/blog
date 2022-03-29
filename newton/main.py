import numpy as np
import matplotlib.pyplot as plt
import matplotlib


def print_hi():
    matplotlib.rcParams['font.family'] = 'YouYuan'
    x = np.arange(-100, 100, 0.1)
    plt.plot(x, x * x - 2, color='red')
    # plt.xlabel('横轴: x', color='green')
    # plt.ylabel('纵轴: y=x^2-2', color='red')
    # plt.axis([-10, 10, -10, 10])
    plt.xlim(-1, 5)
    plt.ylim(-3, 15)

    ax = plt.gca()
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.spines['bottom'].set_position(('data', 0))
    ax.yaxis.set_ticks_position('left')
    ax.spines['left'].set_position(('data', 0))

    plt.annotate(r'(4, 14)',
                 xy=(4, 14), xycoords='data',
                 xytext=(+0, +0), textcoords='offset points', fontsize=12)

    plt.annotate(r'(4, 0)',
                 xy=(4, 0), xycoords='data',
                 xytext=(+0, +0), textcoords='offset points', fontsize=12)
    # arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.1"))

    x1 = np.arange(0, 4.25, 0.1)
    plt.plot(x1, (x1 - 4) * 2 * 4 + 14, 'g--')
    plt.vlines([4], 0, [14], linestyles='dashed', color='blue')

    x2 = 2.25
    # y2 = 3.0625
    x2r = np.arange(0, 2.5, 0.1)
    plt.plot(x2r, (x2r - x2) * 2 * x2 + x2*x2 - 2, 'g--')
    plt.vlines([x2], 0, [x2*x2 - 2], linestyles='dashed', color='blue')

    plt.annotate(r'(2.25, 3.0625)',
                 xy=(2.25, 3.0625), xycoords='data',
                 xytext=(+0, +0), textcoords='offset points', fontsize=12)

    plt.annotate(r'(2.25, 0)',
                 xy=(2.25, 0), xycoords='data',
                 xytext=(+0, +0), textcoords='offset points', fontsize=12)

    plt.show()
    
def print_there():
    matplotlib.rcParams['font.family'] = 'YouYuan'
    x = np.arange(-100, 100, 0.1)
    plt.plot(x, x * x - 2, color='red')
    plt.xlim(-1, 5)
    plt.ylim(-3, 15)

    ax = plt.gca()
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.spines['bottom'].set_position(('data', 0))
    ax.yaxis.set_ticks_position('left')
    ax.spines['left'].set_position(('data', 0))

    plt.annotate(r'(x0, y0)',
                 xy=(4, 14), xycoords='data',
                 xytext=(-40, +15), textcoords='offset points', fontsize=12,
                 arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.1"))
    plt.vlines([4], 0, [14], linestyles='dashed', color='blue')

    plt.annotate(r'(x1, y1)',
                 xy=(2, 2), xycoords='data',
                 xytext=(-40, +15), textcoords='offset points', fontsize=12,
                 arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.1"))
    plt.vlines([2], 0, [2], linestyles='dashed', color='blue')

    plt.annotate(r'(x, y)',
                 xy=(3, 7), xycoords='data',
                 xytext=(+10, +15), textcoords='offset points', fontsize=12,
                 arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.1"))
    plt.vlines([2], 0, [2], linestyles='dashed', color='blue')

    x0 = np.arange(2, 4, 0.1)
    plt.plot(x0, 6*x0-10, 'g--')

    plt.show()


if __name__ == '__main__':
    # print_hi()
    print_there()
