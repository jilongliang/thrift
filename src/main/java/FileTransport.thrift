// -------------------------------------------------------------------- File
struct FileEntity
{
    1:required string   name,                   // 文件名字
    2:required binary   buff,                   // 文件数据
}

// -------------------------------------------------------------------- Service

service FileService
{
	//FileEntity表示上面的struct
    bool uploadFile(1:FileEntity entity);       // 文件解析函数
}