package collectiontest;



public class ArrayListTest {
    /**
     * 存放List中元素的数组
     */
    private transient Object[] elementData;
    /**
     * ArrayList中元素的数量
     */
    private int size;
    //ArrayList默认的初始容量
    private static final int DEFAULT_CAPACITY = 10;
    //这是一个共享的空的数组实例
    private static final Object[] EMPTY_ELEMENTDATA = {};
    //元素数组的最大容量
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     *集合底层实现的数据结构	数组
     *集合中元素是否允许为空	允许
     *是否允许重复的数据	允许
     *是否有序	有序
     *是否线程安全	非线程安全
     */

    /*1. 无参构造函数
    public ArrayList() {
        super();
        this.elementData = EMPTY_ELEMENTDATA;
    }

    这个无参构造函数直接把EMPTY_ELEMENTDATA传递给elementData属性。根据注释说明：构造一个初始容量为10的空列表。至于EMPTY_ELEMENTDATA是什么时候初始化的，且看后面的分析。*/


    /*2. 指定初始容量的构造函数
    public ArrayList(int initialCapacity) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        //new一个指定容量的数组传递给elementData
        this.elementData = new Object[initialCapacity];
    }*/

/*  3. 使用传递的集合初始化List的构造函数
    public ArrayList(Collection<? extends E> c) {
        //将集合c转换成数组元素，然后传递给elementData
        elementData = c.toArray();
        //获取数组的大小，然后赋值给size
        size = elementData.length;
        //这里会进行判断elementData是否正确的被赋值
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    }

 这里有一点值得注意的是最后一句：通过调用Arrays.copyOf()函数复制指定的数组，截取或用 null 填充（如有必要），
 以使副本具有指定的长度。我们进入这个函数发现这个函数主要是调用了System.arraycopy(),继续跟踪下去，发现这个函数是一个native方法。*/
}
