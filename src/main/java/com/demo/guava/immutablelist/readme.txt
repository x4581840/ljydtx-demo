不可变集合，顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。

为什么要用immutable对象？immutable对象有以下的优点：
　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的

immutable对象可以很自然地用作常量，因为它们天生就是不可变的对于immutable对象的运用来说，它是一个很好的防御编程（defensive programming）的技术实践。

Immutable集合使用方法：
　　一个immutable集合可以有以下几种方式来创建：
　　1.用copyOf方法, 譬如, ImmutableSet.copyOf(set)
　　2.使用of方法，譬如，ImmutableSet.of("a", "b", "c")或者ImmutableMap.of("a", 1, "b", 2)
　　3.使用Builder类  