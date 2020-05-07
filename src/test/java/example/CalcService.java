package example;

/**
 * 接口
 *
 * @author YI
 * @date 2020/5/7
 */
public interface CalcService {
    /**
     * 获取IP地址
     *
     * @return
     */
    String getIp();

    /**
     * 打招呼
     *
     * @param name 名称
     * @return
     */
    String hi(String name);
}