package com.zl.vo_.own.ui.mine.bean;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/9/5.
 */

public class LifeNoteBean {
    private String avatar;
    private String nickname;
    private String createTime;
    private String content;
    private List<Uri> pictureList;
    private List<Uri> pictureThumbList;

    public static List<LifeNoteBean> get() {
        List<LifeNoteBean> LifeNoteBeanList = new ArrayList<>();

        LifeNoteBean LifeNoteBean1 = new LifeNoteBean();
        LifeNoteBean1.avatar = "http://b162.photo.store.qq.com/psb?/V14EhGon4cZvmh/z2WukT5EhNE76WtOcbqPIgwM2Wxz4Tb7Nub.rDpsDgo!/b/dOaanmAaKQAA";
        LifeNoteBean1.nickname = "萌新-lpe";
        LifeNoteBean1.createTime = "昨天 11:21";
        LifeNoteBean1.content = "开司还是那么帅";
        LifeNoteBean1.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664940_9893.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664940_3308.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664927_3920.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664926_8360.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664926_6184.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664925_8382.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664925_2087.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664777_5730.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664741_1378.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671689_9534.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671689_2126.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671703_7890.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201707/27/1501118577_9169.jpg") // 超出屏幕宽度大尺寸图测试
        );
        LifeNoteBean1.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664940_9893.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647798_4500.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647897_1367.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484650736_2101.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647701_9893.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484650700_2514.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647930_5139.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647929_8108.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647897_1978.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647898_4474.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647930_7735.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647929_9591.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201707/27/1501118720_9504.jpg") // 超出屏幕宽度大尺寸图测试
        );


        LifeNoteBean LifeNoteBean2 = new LifeNoteBean();
        LifeNoteBean2.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        LifeNoteBean2.nickname = "苦涩";
        LifeNoteBean2.createTime = "昨天 09:59";
        LifeNoteBean2.content = "唐僧还是厉害啊。为什么？有宝马";
        LifeNoteBean2.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483664741_7475.png")
        );
        LifeNoteBean2.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647799_1689.png")
        );

        LifeNoteBean LifeNoteBean3 = new LifeNoteBean();
        LifeNoteBean3.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        LifeNoteBean3.nickname = "empty";
        LifeNoteBean3.createTime = "昨天 08:12";
        LifeNoteBean3.content = "各种眼神";
        LifeNoteBean3.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671690_1970.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671690_6902.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671702_6499.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/06/1483671702_2352.jpg")
        );
        LifeNoteBean3.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484650701_4150.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484650719_9275.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647702_8420.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647930_4474.jpg")
        );

        LifeNoteBean LifeNoteBean4 = new LifeNoteBean();
        LifeNoteBean4.avatar = "http://b167.photo.store.qq.com/psb?/V14EhGon2OmAUI/hQN450lNoDPF.dO82PVKEdFw0Qj5qyGeyN9fByKgWd0!/m/dJWKmWNZEwAAnull";
        LifeNoteBean4.nickname = "empty";
        LifeNoteBean4.createTime = "昨天 06:00";
        LifeNoteBean4.content = "人与人间的信任，就像是纸片，一旦破损，就不会再回到原来的样子。";
        LifeNoteBean4.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296303_7395.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296122_9613.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296303_6984.png")
        );
        LifeNoteBean4.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647817_3557.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647818_9583.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647817_7305.png")
        );

        LifeNoteBean LifeNoteBean5 = new LifeNoteBean();
        LifeNoteBean5.avatar = "http://qlogo3.store.qq.com/qzone/383559698/383559698/100?1416542262";
        LifeNoteBean5.nickname = "越线龙马";
        LifeNoteBean5.createTime = "前天 14:61";
        LifeNoteBean5.content = "雪.触之即化..愿永久飘零";
        LifeNoteBean5.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296288_3031.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296303_5044.jpg")
        );
        LifeNoteBean5.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647278_8869.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647702_1117.jpg")
        );

        LifeNoteBean LifeNoteBean6 = new LifeNoteBean();
        LifeNoteBean6.avatar = "http://b162.photo.store.qq.com/psb?/V14EhGon4cZvmh/z2WukT5EhNE76WtOcbqPIgwM2Wxz4Tb7Nub.rDpsDgo!/b/dOaanmAaKQAA";
        LifeNoteBean6.nickname = "顺子要不起";
        LifeNoteBean6.createTime = "圣诞节";
        LifeNoteBean6.content = "颜宇扬的期末总结\n1、有本事冲我来，别再家长会上吓唬我爸\n2、期末考试成绩出来了，我觉得我妈生二胎是非常明智的选择\n3、这场考试对于我的意义就是知道了班上到底有多少人\n4、期末考试不给老师们露一手，他们还真以为自己教的好";
        LifeNoteBean6.pictureList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296287_2190.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296286_7908.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296286_7013.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296286_6401.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/13/1484296106_5671.png")
        );
        LifeNoteBean6.pictureThumbList = Arrays.asList(
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647898_9300.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647278_2143.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647816_4929.png"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647817_5319.jpg"),
                Uri.parse("http://img.my.csdn.net/uploads/201701/17/1484647818_3369.png")
        );


        if (System.currentTimeMillis() % 3 == 0) {
            LifeNoteBeanList.add(LifeNoteBean1);
            LifeNoteBeanList.add(LifeNoteBean2);
            LifeNoteBeanList.add(LifeNoteBean3);
            LifeNoteBeanList.add(LifeNoteBean4);
            LifeNoteBeanList.add(LifeNoteBean6);
        } else if (System.currentTimeMillis() % 3 == 1) {
            LifeNoteBeanList.add(LifeNoteBean5);
            LifeNoteBeanList.add(LifeNoteBean6);
            LifeNoteBeanList.add(LifeNoteBean2);
            LifeNoteBeanList.add(LifeNoteBean4);
            LifeNoteBeanList.add(LifeNoteBean3);
        } else {
            LifeNoteBeanList.add(LifeNoteBean1);
            LifeNoteBeanList.add(LifeNoteBean3);
            LifeNoteBeanList.add(LifeNoteBean4);
            LifeNoteBeanList.add(LifeNoteBean5);
            LifeNoteBeanList.add(LifeNoteBean6);
        }

        return LifeNoteBeanList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Uri> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Uri> pictureList) {
        this.pictureList = pictureList;
    }

    public List<Uri> getPictureThumbList() {
        return pictureThumbList;
    }

    public void setPictureThumbList(List<Uri> pictureThumbList) {
        this.pictureThumbList = pictureThumbList;
    }
}
