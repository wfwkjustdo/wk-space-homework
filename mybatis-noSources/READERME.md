# 1、resultType（属性）和resultMap（标签引用）的区别？

* resultMap：表示返回对象是自定义的映射对象
* resultType：jdk 原生对象，支持将结果集映射到普通java对象，也可以是基本类型

# 2、collection和association的区别？

* collection ：表示是个集合1对多
* association：表示返回的单个对象1对1 

# 3、Statement和PreparedStatement的区别？

* PreparedStatement 预编译，占位符可以防止sql 注入
* Statement 有sql注入风险