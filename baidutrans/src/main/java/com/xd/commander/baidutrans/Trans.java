package com.xd.commander.baidutrans;
import java.util.List;

/**
 * @author Administrator 23:26 2018/1/2
 */
public class Trans {

    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"fuck you","dst":"你他妈的"}]
     */

    private String from;
    private String to;
    private List<TransResultBean> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }


    public static class TransResultBean {

        private String src;
        private String dst;

        public String getDst() {
            return dst;
        }

    }
}

