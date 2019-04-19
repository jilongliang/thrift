// -------------------------------------------------------------------- File
struct RpcUserEntity
{
    1:required string   userName,                  
    2:required string   passWord,                    
}

// -------------------------------------------------------------------- Service

service RpcUserService
{
	//RpcUserEntity
    List<1:RpcUserEntity> uploadFile(2:RpcUserEntity entity);       // 文件解析函数
}