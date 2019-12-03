package com.jack.utils.log;

/**
 * @author ：liyongjie
 * @ClassName ：LogMessage
 * @date ： 2019-11-28 09:49
 * @modified By：
 */
public class LogMessage {
    public static final LogMessage DYNAMIC_DS_DEBUG = new LogMessage("101001", "动态数据源");
    public static final LogMessage REDIS_FACTORY_INFO = new LogMessage("201001", "RedisFactory信息");
    public static final LogMessage MONGODB_FACTORY_INFO = new LogMessage("201002", "MongoDBFactoryBean信息");
    public static final LogMessage AUTH_INTECEPTOR_WARN = new LogMessage("301001", "权限拦截警告");
    public static final LogMessage TRACK_INTECEPTOR_WARN = new LogMessage("301002", "Track拦截警告");
    public static final LogMessage REDIS_CLIENT_CONFIG_ERROR = new LogMessage("401001", "客户端读取Redis配置信息错误");
    public static final LogMessage REDIS_PUBLISH_ERROR = new LogMessage("401002", "Redis发布信息错误");
    public static final LogMessage UNEXPECTED_ERROR = new LogMessage("401003", "系统错误");
    public static final LogMessage JDBC_CONN_EXCEPTION = new LogMessage("401004", "数据库连接异常");
    public static final LogMessage SERVICE_EXCEPTION = new LogMessage("401005", "业务异常");
    public static final LogMessage VERSION_EXCEPTION = new LogMessage("401006", "乐观锁异常");
    public static final LogMessage OSS_EXCEPTION = new LogMessage("401007", "OSS异常");
    public static final LogMessage EXCEL_EXCEPTION = new LogMessage("401008", "Excel处理异常");
    public static final LogMessage REDIS_EXCEPTION = new LogMessage("401009", "Redis处理异常");
    public static final LogMessage PARAM_VALIDATE_EXCEPTION = new LogMessage("401010", "参数验证异常");
    public static final LogMessage REDIS_LOCK_EXCEPTION = new LogMessage("401011", "Redis分布式锁异常");
    public static final LogMessage JSON_PARSE_EXCEPTION = new LogMessage("401012", "JSON转换异常");
    public static final LogMessage TASK_TOKEN_INFO = new LogMessage("202001", "Token认证");
    public static final LogMessage TASK_TOKEN_FAIL = new LogMessage("402001", "Token认证失败");
    public static final LogMessage API_CURRENCY_ADD_INFO = new LogMessage("202002", "虚拟货币收款信息");
    public static final LogMessage API_CURRENCY_ADD_FAIL = new LogMessage("302002", "虚拟货币收款失败");
    public static final LogMessage API_CURRENCY_ADD_ERROR = new LogMessage("402002", "虚拟货币收款异常");
    public static final LogMessage API_CURRENCY_REDUCE_INFO = new LogMessage("202003", "虚拟货币付款信息");
    public static final LogMessage API_CURRENCY_REDUCE_FAIL = new LogMessage("302003", "虚拟货币付款失败");
    public static final LogMessage API_CURRENCY_REDUCE_ERROR = new LogMessage("402003", "虚拟货币付款异常");
    public static final LogMessage API_CURRENCY_REFUND_INFO = new LogMessage("202004", "虚拟货币退款信息");
    public static final LogMessage API_CURRENCY_REFUND_FAIL = new LogMessage("302004", "虚拟货币退款失败");
    public static final LogMessage API_CURRENCY_REFUND_ERROR = new LogMessage("402004", "虚拟货币退款异常");
    public static final LogMessage API_CURRENCY_QTY_QUERY_INFO = new LogMessage("202005", "虚拟货币查询信息");
    public static final LogMessage API_CURRENCY_QTY_QUERY_FAIL = new LogMessage("302005", "虚拟货币查询失败");
    public static final LogMessage API_CURRENCY_QTY_QUERY_ERROR = new LogMessage("402005", "虚拟货币查询异常");
    public static final LogMessage API_CURRENCY_PAY_LOG_QUERY_INFO = new LogMessage("202006", "支付流水查询信息");
    public static final LogMessage API_CURRENCY_PAY_LOG_QUERY_FAIL = new LogMessage("302006", "支付流水查询失败");
    public static final LogMessage API_CURRENCY_PAY_LOG_QUERY_ERROR = new LogMessage("402006", "支付流水查询异常");
    public static final LogMessage API_PAY_PASSWORD_SETTING_INFO = new LogMessage("202007", "支付密码设定信息");
    public static final LogMessage API_PAY_PASSWORD_SETTING_FAIL = new LogMessage("302007", "支付密码设定失败");
    public static final LogMessage API_PAY_PASSWORD_SETTING_ERROR = new LogMessage("402007", "支付密码设定异常");
    public static final LogMessage API_IS_REDUCE_INFO = new LogMessage("202008", "是否付款查询信息");
    public static final LogMessage API_IS_REDUCE_FAIL = new LogMessage("302008", "是否付款查询失败");
    public static final LogMessage API_IS_REDUCE_ERROR = new LogMessage("402008", "是否付款查询异常");
    public static final LogMessage SERVICE_SEND_SMS_INFO = new LogMessage("500000", "调用短信接口成功");
    public static final LogMessage SERVICE_SEND_SMS_ERROR = new LogMessage("500001", "调用短信接口失败");
    public static final LogMessage SERVICE_SEND_MAIL_ERROR = new LogMessage("600001", "发送邮件失败");
    public static final LogMessage TASK_PRODUCT_CONSUME_TASK_INFO = new LogMessage("299099", "生产者消费者任务信息");
    public static final LogMessage TASK_PRODUCT_CONSUME_TASK_WARN = new LogMessage("399099", "生产者消费者任务警告");
    public static final LogMessage TASK_PRODUCT_CONSUME_TASK_ERROR = new LogMessage("499099", "生产者消费者任务异常");
    public static final LogMessage TASK_DISTRIBUTION_SYNC_MALL_INFO = new LogMessage("299001", "同步O2O商城经销商Task信息");
    public static final LogMessage TASK_DISTRIBUTION_SYNC_MALL_ERROR = new LogMessage("499001", "同步O2O商城经销商Task异常");
    public static final LogMessage TASK_ACHIEVEMENT_CLEARING_INFO = new LogMessage("299002", "业绩结算信息");
    public static final LogMessage TASK_ACHIEVEMENT_CLEARING_ERROR = new LogMessage("499002", "业绩结算异常");
    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    private LogMessage(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
