
## Thrift简要
* [thrift官方](http://thrift.apache.org/)
* Apache Thrift软件框架，用于可扩展的跨语言服务开发，将软件堆栈与代码生成引擎相结合，构建可在C ++，Java，Python，PHP，Ruby，Erlang，Perl，Haskell，C＃之间高效无缝工作的服务， Cocoa，JavaScript，Node.js，Smalltalk，OCaml和Delphi等语言。
* Thrift 是由Facebook开发的个框架，后贡献了Apache进行维护

## Thrift特征（掌握点）
* Thrift跟protobuf同样有自己的生成编译器以.thrif后缀文件
* Thrift是一个典型的CS结构，客户端和服务器可以用不同Language开发，使用一种中间Language来关联客户端和服务端的语言，这种语言就是IDL（Inteface Description Language）
* Thrift不支持无符号类型，很多语言都不存在无符号的类型，如：Java语言

## Thrift的容器类型
* Thrift的容器类型，集合中的元素可以是除了service之外的任何类型，包括Exception
* List set map

## Thrift的数据类型（掌握点）
* byte 有符号的字节
* i16 16位有符号的整数
* i32 32位有符号的整数
* i64 64为有符号的整数
* double 64位浮点数
* string 字符串

## Thrift如何实现多语言之间的通信（重点）
* 数据传输使用Socket（多种语言的支持）数据再以特定的的格式（String等）发送，接收方语言进行解析

## Thrift工作原理（重点）
* 定义thrift文件，有thrift文件（IDL）生成双方不同语言的接口，Model，在生成Model以及接口中的**解码和编码**的代码

## Thrift语法与使用
* 1、结构体（struct） 目的将一些数据聚合在一起，方便传输管理
* 2、枚举、Exception规则与strut一样
* 3、服务于Java创建Interface一样
* 4、类型定义，类似c++ 一样，用typedef关键字对字段进行（起别名）
* 5、常量：使用const关键字
* 6、命名空间：组织代码，格式： namespace  语言名  路径
* 7、文件包含(include)，相当于c / c++ 中的include， Java中的import使用。
* 8、注释：支持shell风格注释，支持C/C++风格的注释，即#和//开头的语句 /**/包裹的语句注释
* 9、requied 必填，optional可选 (在开发过程中可选推荐使用)
* 10、**Thrift不支持Date类型，使用String的约束，用代码进行转换**


## Thrift重要的类说明（重点）
* THsHaServer高可用的一个服务类（半同步，半异步）
* TNonblockingServerSocket 非阻塞
* serve方法是一个死循环
* TCompactProtocol压缩协议，客户端和服务端的协议**必须是要一致**
* TBinaryProtocol 二进制格式
* TSimpleJSONProtocol 提供只写协议，生成一个文件，很容易通过传输方式脚本语言进行解析
* TDebugProtocol使用易懂的可读文本格式，便于debug调试
* TSocket 阻塞式的Socket
* TFramedTancsport 以fram为单位进行传输**非阻塞式**服务中，使用一端向另外一端传输数据会切割frame的单位
* TFileTransport 以文件进行传输
* TMemoryTransport 将内存用于I/O 
* TZlibTransport 使用zlib进行压缩与其他传输方式使用

## 支持的服务模型
* TSimpleServer 简单的单线程服务模型，常用于test
* TThreadPoolServer 多线程服务模型使用标准的阻塞IO
* TNonblockingServer 多线程服务模型使用非阻塞IO，需要使用TFramedTransport数据传输方式
* THsHaServer - THsHa引入了线程池去处理，其模型把读写任务放到线程池去处理，Half-sync / Half-async的处理模型，Half-aysnc在处理IO事件上（accep、read、write ）Half-sync用于Handler对RPC同步处理


## thrift IDL文件
```

namespace java com.flong.thrift.service.struct
namespace php com.flong.thrift.service.struct

const string VERSION = "1.0.1"

/****************************************************************************************************
* 为ThriftResult添加数据不完全和内部错误两种类型
* 枚举类型ThriftResult，表示返回结果，成功或失败，如果失败，还可以表示失败原因
* 每种返回类型都对应一个封装的结构体，该结构体其命名遵循规则："Result" + "具体操作结果类型"，结构体都包含两部分内容：
* 第一部分为枚举类型ThriftResult变量result，表示操作结果,可以 表示成功，或失败，失败时可以给出失败原因
* 第二部分的变量名为value，表示返回结果的内容；
*****************************************************************************************************/
enum ThriftResult
{
  SUCCESS,           /**成功*/
  SERVER_UNWORKING,  /**服务器处于非Working状态*/
  NO_CONTENT,  		 /**请求结果不存在*/
  PARAMETER_ERROR,	 /**参数错误*/
  EXCEPTION,	 	 /**内部出现异常*/
  INDEX_ERROR,		 /**错误的索引或者下标值*/
  UNKNOWN_ERROR 	 /**未知错误*/
  DATA_NOT_COMPLETE 	 /**数据不完全*/
  INNER_ERROR 	 /**内部错误*/
}

/**bool类型返回结果*/
struct ResultBool 
{
  1: ThriftResult result,
  2: bool value
}

/**int类型返回结果*/
struct ResultInt
{
  1: ThriftResult result,
  2: i32 value
}

/**String类型返回结果*/
struct ResultStr
{
  1: ThriftResult result,
  2: string value
}

/**long类型返回结果*/
struct ResultLong
{
  1: ThriftResult result,
  2: i64 value
}

/**double类型返回结果*/
struct ResultDouble
{
  1: ThriftResult result,
  2: double value
}

/**list<string>类型返回结果*/
struct ResultListStr 
{
  1: ThriftResult result,
  2: list<string> value
}

/**Set<string>类型返回结果*/
struct ResultSetStr 
{
  1: ThriftResult result,
  2: set<string> value
}

/**map<string,string>类型返回结果*/
struct ResultMapStrStr 
{
  1: ThriftResult result,
  2: map<string,string> value
}


```
```
namespace java com.flong.thrift.service
include "ThriftDataType.thrift"
service ThriftService
{
	/**
	*value 中存放两个字符串拼接之后的字符串
	*/
	ThriftDataType.ResultStr getStr(1:string srcStr1, 2:string srcStr2),
	ThriftDataType.ResultInt getInt(1:i32 val)
}
```
## 使用thrift生成的代码结构
```
import java.io.IOException;

public class GenerateClass {
	public static void main(String[] args) {
		String thrifFile = "ThriftService.thrift";//
		//String strCmd = "./src/main/java/thrift-0.9.3.exe -r -gen java ./src/main/java/"+thrifFile;
		String strCmd = "./src/main/java/thrift-0.9.3.exe -r -gen php ./src/main/java/"+thrifFile;
		
		cmd2(strCmd);
	}
	
	protected static void cmd2(String thrifFile) {
		//thrift-0.9.3.exe -r -gen java D:\work\workspace\thriftworkspace\demo1\DemoHello.thrift
		
		try {
			Runtime.getRuntime().exec(thrifFile);
			System.out.println("执行完===");
		} catch (IOException e) {
			e.printStackTrace();
		} // 通过执行cmd命令调用protoc.exe程序
	}
}
```

![thrift编辑器](https://upload-images.jianshu.io/upload_images/14586304-ff5a39aba090497c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![编辑器生成的结构](https://upload-images.jianshu.io/upload_images/14586304-300b6c22b91ee807.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 推荐学习的博客
* [文章1](https://www.ibm.com/developerworks/cn/java/j-lo-apachethrift/index.html) 
* [文章2](https://www.cnblogs.com/duanxz/p/5516558.html)
* [文章3](http://www.cnblogs.com/duanxz/tag/)
* [文章4](http://www.cnblogs.com/duanxz/tag/JVM%E8%99%9A%E6%8B%9F%E6%9C%BA/)
* [文章5](http://blog.csdn.net/houjixin/article/details/42778335)







