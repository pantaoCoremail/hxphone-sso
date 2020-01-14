package cn.lunkr.hxphone;

/**
 * Created by admkin on 2019/12/27
 */
public class Demo {

    public static void main(String[] args) {
        //获取单点sid
        SSOResult result = HXphoneUtil.getSSOSid("192.168.200.251", "test@test.com");
        System.out.println(result.getSid());

        //获取收件箱地址
        SSOResult result1 = HXphoneUtil.getMainURL("192.168.200.251", "test@test.com",
                "http://192.168.200.251/coremail");
        System.out.println(result1.getUrl());

        //获取写信地址
        SSOResult result2 = HXphoneUtil.getComposeURL("192.168.200.251", "test@test.com",
                "http://192.168.200.251/coremail");
        System.out.println(result2.getUrl());

        //获取写信地址(自动填充收信人)
        SSOResult result3 = HXphoneUtil.getComposeURL("192.168.200.251", "test@test.com",
                "http://192.168.200.251/coremail", "test@test.com,test1@test.com");
        System.out.println(result3.getUrl());

        //获取单点读信地址
        SSOResult result4 = HXphoneUtil.getReadMsgURL("192.168.200.251", "test@test.com",
                "http://192.168.200.251/coremail", "1:1tbiAQAAAFuJfpsARQA9sO");
        System.out.println(result4.getUrl());
    }

}
