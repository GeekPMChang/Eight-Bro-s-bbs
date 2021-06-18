# Junit的基本使用

## 简介

> JUnit 是一个 Java 编程语言的单元测试框架。JUnit 在测试驱动的开发方面有很重要的发展，是起源于 JUnit 的一个统称为 xUnit 的单元测试框架之一。

## 好处

1. 可以书写一系列的测试方法，对项目所有的接口或者方法进行单元测试。
2. 启动后，自动化测试，并判断执行结果, 不需要人为的干预。
3. 只需要查看最后结果，就知道整个项目的方法接口是否通畅。
4. 每个单元测试用例相对独立，由Junit 启动，自动调用。不需要添加额外的调用语句。
5. 添加，删除，屏蔽测试方法，不影响其他的测试方法。 开源框架都对JUnit 有相应的支持。

## 环境配置

从官网[http://www.junit.org](http://www.junit.org/) 下载 JUnit 最新版本的压缩文件。

加载方式：

```xml
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

## 基本用法

测试代码和生成代码分开放置，Maven默认目录正好符号这个要求。

![img](https:////upload-images.jianshu.io/upload_images/1248990-d77edb8f2167ecee.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/560)

代码目录

被测试代码放在`main`下的`java`目录中，junit测试代码放在`test`下的`java`目录中，形成一一对应关系，测试代码使用`Test`开头命名。

被测试`MessageDemo`代码：

![img](https:////upload-images.jianshu.io/upload_images/1248990-8a20e3c4e0d90bf1.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)

MessageDemo

测试`TestMessageDemo`代码：

![img](https:////upload-images.jianshu.io/upload_images/1248990-d91ef9a606dd483d.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)

MessageDemo

测试之前还需要建一个运行测试的文件：

![img](https:////upload-images.jianshu.io/upload_images/1248990-0d280a10b04cd21f.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)

MessageDemo

运行测试：



![img](https:////upload-images.jianshu.io/upload_images/1248990-e366806277298a60.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/350)

控制台输出

看到`true`表明测试成功

## JUnit 断言

Junit所有的断言都包含在 Assert 类中。

这个类提供了很多有用的断言方法来编写测试用例。只有失败的断言才会被记录。Assert 类中的一些有用的方法列式如下：

1. `void assertEquals(boolean expected, boolean actual)`:检查两个变量或者等式是否平衡
2. `void assertTrue(boolean expected, boolean actual)`:检查条件为真
3. `void assertFalse(boolean condition)`:检查条件为假
4. `void assertNotNull(Object object)`:检查对象不为空
5. `void assertNull(Object object)`:检查对象为空
6. `void assertSame(boolean condition)`:assertSame() 方法检查两个相关对象是否指向同一个对象
7. `void assertNotSame(boolean condition)`:assertNotSame() 方法检查两个相关对象是否不指向同一个对象
8. `void assertArrayEquals(expectedArray, resultArray)`:assertArrayEquals() 方法检查两个数组是否相等

## JUnit 注解

1. `@Test`:这个注释说明依附在 JUnit 的 public void 方法可以作为一个测试案例。
2. `@Before`:有些测试在运行前需要创造几个相似的对象。在 public void 方法加该注释是因为该方法需要在 test 方法前运行。
3. `@After`:如果你将外部资源在 Before 方法中分配，那么你需要在测试运行后释放他们。在 public void 方法加该注释是因为该方法需要在 test 方法后运行。
4. `@BeforeClass`:在 public void 方法加该注释是因为该方法需要在类中所有方法前运行。
5. `@AfterClass`:它将会使方法在所有测试结束后执行。这个可以用来进行清理活动。
6. `@Ignore`:这个注释是用来忽略有关不需要执行的测试的。

### JUnit 加注解执行过程

- `beforeClass()`: 方法首先执行，并且只执行一次。
- `afterClass()`:方法最后执行，并且只执行一次。
- `before()`:方法针对每一个测试用例执行，但是是在执行测试用例之前。
- `after()`:方法针对每一个测试用例执行，但是是在执行测试用例之后。
- 在 before() 方法和 after() 方法之间，执行每一个测试用例。

## JUnit 执行测试

测试用例是使用 JUnitCore 类来执行的。JUnitCore 是运行测试的外观类。要从命令行运行测试，可以运行`java org.junit.runner.JUnitCore`。对于只有一次的测试运行，可以使用静态方法 `runClasses(Class[])`。

## JUnit 时间测试

如果一个测试用例比起指定的毫秒数花费了更多的时间，那么 Junit 将自动将它标记为失败。`timeout`参数和 `@Test` 注释一起使用。

就像这样：

```css
@Test(timeout=1000)
```

时间单位是毫秒。

## JUnit 异常测试

Junit 用代码处理提供了一个追踪异常的选项。我们可以测试代码是否它抛出了想要得到的异常。`expected` 参数和 `@Test` 注释一起使用。

比如这样：

```ruby
@Test(expected = ArithmeticException.class)
```

## JUnit 参数化测试

Junit 4 引入了一个新的功能参数化测试。参数化测试允许开发人员使用不同的值反复运行同一个测试。你将遵循 5 个步骤来创建参数化测试。

- 用 `@RunWith(Parameterized.class)` 来注释 test 类。
- 创建一个由 `@Parameters`注释的公共的静态方法，它返回一个对象的集合(数组)来作为测试数据集合。
- 创建一个公共的构造函数，它接受和一行测试数据相等同的东西。
- 为每一列测试数据创建一个实例变量。
- 用实例变量作为测试数据的来源来创建你的测试用例。

一旦每一行数据出现测试用例将被调用。