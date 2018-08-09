AtomicBoolean是java.util.concurrent.atomic包下的原子变量，
这个包里面提供了一组原子类。其基本的特性就是在多线程环境下，
当有多个线程同时执行这些类的实例包含的方法时，具有排他性，即当某个线程进入方法，
执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，
一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入，
这只是一种逻辑上的理解。实际上是借助硬件的相关指令来实现的，
不会阻塞线程(或者说只是在硬件级别上阻塞了)

例如AtomicBoolean，在这个Boolean值的变化的时候不允许在之间插入，保持操作的原子性。
方法和举例：compareAndSet(boolean expect, boolean update)。这个方法主要两个作用
1. 比较AtomicBoolean和expect的值，如果一致，执行方法内的语句。其实就是一个if语句
2. 把AtomicBoolean的值设成update
比较最要的是这两件事是一气呵成的，这连个动作之间不会被打断，
任何内部或者外部的语句都不可能在两个动作之间运行。为多线程的控制提供了解决的方案

可以用原子方式更新的 boolean 值。有关原子变量属性的描述，请参阅 java.util.concurrent.atomic 包规范。AtomicBoolean 可用在应用程序中（如以原子方式更新的标志），但不能用于替换 Boolean。

2.构造函数

　　1.AtomicBoolean()

　　　　使用初始值 false 创建新的 AtomicBoolean。

　　2.AtomicBoolean(boolean initialValue)

　　　　使用给定的初始值创建新的 AtomicBoolean。

3.方法详解

get
public final boolean get()
返回当前值。


返回：
当前值

compareAndSet
public final boolean compareAndSet(boolean expect,
                                   boolean update)
如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。


参数：
expect - 预期值
update - 新值
返回：
如果成功，则返回 true。返回 False 指示实际值与预期值不相等。

weakCompareAndSet
public boolean weakCompareAndSet(boolean expect,
                                 boolean update)
如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
可能意外失败并且不提供排序保证，因此几乎只是 compareAndSet 的适当替代方法。

参数：
expect - 预期值
update - 新值
返回：
如果成功，则返回 true。

set
public final void set(boolean newValue)
无条件地设置为给定值。


参数：
newValue - 新值

lazySet
public final void lazySet(boolean newValue)
最终设置为给定值。


参数：
newValue - 新值
从以下版本开始：
1.6

getAndSet
public final boolean getAndSet(boolean newValue)
以原子方式设置为给定值，并返回以前的值。


参数：
newValue - 新值
返回：
以前的值

toString
public String toString()
返回当前值的字符串表示形式。


覆盖：
类 Object 中的 toString
返回：
当前值的字符串表示形式。