
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
