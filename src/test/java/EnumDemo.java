/**
 * @program: 00_Spark
 * @description: 枚举类型演示
 * @author: hupeng
 * @create: 2018-12-17 17:15
 **/
public class EnumDemo {

    public static void main(String[] args) {
        Stream event = Stream.FeedbackEventStream;
        System.out.println(event);
        System.out.println(event.name());
        System.out.println(event.ordinal());
        System.out.println(Stream.BusinessEventStream.ordinal());
        System.out.println(Stream.values());
        System.out.println(Stream.valueOf("FeedbackEventStream"));
    }

    public enum Stream {
        FeedbackEventStream, // 反馈事件
        BusinessEventStream, // 行为事件匹配策略地图
        ActivityFtpStream, // 文件处理
        ActivityStartStream, // 活动上线
        ActivityDownStream, // 活动下线
        ContactStatusStream //工单失败状态检查
    }
}
