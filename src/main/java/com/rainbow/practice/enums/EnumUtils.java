package com.rainbow.practice.enums;


/**
 * 枚举相关
 * @author yanzhihao
 */
public class EnumUtils {

    /**
     * 可以使用枚举code获取枚举对象，没有匹配的code返回null<br/>
     *  使用方式：<br/>
     *  实现EnumCode接口，并用泛型指明code类型<br/>
     *  eg:
     *  <pre>public enum WorkflowUserType implements EnumCode<String> {}</pre><br/>
     *  然后可以调用该方法即可完成转换<br/>
     *  eg:
     *  <pre>WorkflowUserType user = EnumUtil.getEnumByCode("USER", WorkflowUserType.class)</pre>
     *
     * @param code: 枚举code
     * @param enumClass: 枚举类型
     */
    public static <T extends EnumCode<R>, R> T getEnumByCode(R code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }

        return null;
    }
}