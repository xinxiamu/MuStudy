http://blog.didispace.com/springcloud4/


URL与配置文件的映射关系如下：

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
上面的url会映射{application}-{profile}.properties对应的配置文件，{label}对应git上不同的分支，默认为master。

spring.application.name：对应前配置文件中的{application}部分
spring.cloud.config.profile：对应前配置文件中的{profile}部分
spring.cloud.config.label：对应前配置文件的git分支
spring.cloud.config.uri：配置中心的地址
