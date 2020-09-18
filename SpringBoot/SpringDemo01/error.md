###1.在编写第一个HelloWorld程序时报
Your ApplicationContext is unlikely to start due to a @ComponentScan of the default package
解决方法：方法一、将Application建在其他的包下面
        方法二、在Application类上面加@ComponentScan注解，指定要扫描的包