

https://www.jianshu.com/p/e2db663daed1


@Autowired默认byType自动注入，如果实例结果不唯一，那么将会抛出异常

@Autowired可与@Qualifier("beanName")搭配使用,注入指定bean。
@Autowired
@Qualifier("baseDao")
private BaseDao baseDao;

如，同一个接口，两个实现类，就可以使用该方式指定注入。
@Autowired(required=false)表示如果spring上下文中没有找到该类型的bean ，将会使用
new SoftPMServiceImpl();

private ISoftPMService softPMService = new SoftPMServiceImpl();


@Resource默认按byName自动注入
但是@Resource有两个属性是比较重要的，分别是name和type；
如果使用name属性，则使用byName的自动注入策略; .
而使用type属性时则使用byType自动注入策略;

@Resource装配顺序
　　1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
　　2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
　　3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
　　4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
使用方式如下：
//@Resource(name="dataSource");

@Resource(type="DataSource.class");

@Resource
private DataSource dataSource;
//inject the bean named 'dataSource';


@Autowired是spring自己定义的注解，@Resource是J2EE的，由JSR-250规范定义
