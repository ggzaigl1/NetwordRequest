package com.fei.networdrequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 初夏小溪
 * @date 2018/4/13 0013
 * 实体对象
 */

public class GanBean implements Serializable{

    /**
     * data : [{"_id":"5e958f3b17bf93950887f20a","author":"鸢媛","category":"Girl","createdAt":"2020-04-20 08:00:00","desc":"单身的时候好好经营自己，\n未来遇到那个值得你爱的人，才不会怯场。","images":["http://gank.io/images/5a29ab0fc093408c82febe7c7e42e156"],"likeCounts":0,"publishedAt":"2020-04-20 08:00:00","stars":1,"title":"第61期","type":"Girl","url":"http://gank.io/images/5a29ab0fc093408c82febe7c7e42e156","views":31},{"_id":"5e958f2dee6ba981da2af33a","author":"鸢媛","category":"Girl","createdAt":"2020-04-19 08:00:00","desc":"对任何热爱的事情都要全力以赴/包括喜欢自己。 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/a2d3115b8d464d93933e79e88af03580"],"likeCounts":2,"publishedAt":"2020-04-19 08:00:00","stars":1,"title":"第60期","type":"Girl","url":"http://gank.io/images/a2d3115b8d464d93933e79e88af03580","views":161},{"_id":"5e958f1eee6ba981da2af339","author":"鸢媛","category":"Girl","createdAt":"2020-04-18 08:00:00","desc":"每一个爱情故事的开始总是灿烂如花，\n而结尾却又总是沉默如土。","images":["http://gank.io/images/fae111696a8b418297833324ff93bd1a"],"likeCounts":0,"publishedAt":"2020-04-18 08:00:00","stars":1,"title":"第59期","type":"Girl","url":"http://gank.io/images/fae111696a8b418297833324ff93bd1a","views":136},{"_id":"5e958f030bd5529b54e7129e","author":"鸢媛","category":"Girl","createdAt":"2020-04-17 08:00:00","desc":"不一定要爱上一个漂亮的人\n但要爱上一个\n使你的生活变漂亮的人 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/c6d3b2b6b5e24e1cbf576946dbec5907"],"likeCounts":3,"publishedAt":"2020-04-17 08:00:00","stars":1,"title":"第58期","type":"Girl","url":"http://gank.io/images/c6d3b2b6b5e24e1cbf576946dbec5907","views":151},{"_id":"5e958ef60bd5529b54e7129d","author":"鸢媛","category":"Girl","createdAt":"2020-04-16 08:00:00","desc":"愿你一生有山可靠 有树可栖\n与心爱之人 春赏花\n夏纳凉 秋登山 冬扫雪 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/e941fa5d2cfb4a8297128178c371908c"],"likeCounts":0,"publishedAt":"2020-04-16 08:00:00","stars":1,"title":"第57期","type":"Girl","url":"http://gank.io/images/e941fa5d2cfb4a8297128178c371908c","views":272},{"_id":"5e958ee017bf93950887f208","author":"鸢媛","category":"Girl","createdAt":"2020-04-15 08:00:00","desc":"愿所期之事皆能成，所念之人皆能到。 ","images":["http://gank.io/images/28fc02e86d584ff08802c8dcd9535b35"],"likeCounts":0,"publishedAt":"2020-04-15 08:00:00","stars":1,"title":"第56期","type":"Girl","url":"http://gank.io/images/28fc02e86d584ff08802c8dcd9535b35","views":230},{"_id":"5e958ec30bd5529b54e7129c","author":"鸢媛","category":"Girl","createdAt":"2020-04-14 08:00:00","desc":"如果一段感情，\n没能把你变成更好的人，\n只是让你患得患失 喜怒无常，\n那真遗憾 你爱错了人。","images":["http://gank.io/images/8a9837115fb64d22b0484e3d4c4cab50"],"likeCounts":1,"publishedAt":"2020-04-14 08:00:00","stars":1,"title":"第55期","type":"Girl","url":"http://gank.io/images/8a9837115fb64d22b0484e3d4c4cab50","views":231},{"_id":"5e8c80cb31ec89ebfc601f12","author":"鸢媛","category":"Girl","createdAt":"2020-04-13 08:00:00","desc":"\u201c 再等一下，上帝肯定会把最特别的你，送给一个最爱你且最特别的人。\u201d \u200b\u200b\u200b\u200b","images":["http://gank.io/images/5888858f49bd4608b12633115687ddc3"],"likeCounts":0,"publishedAt":"2020-04-13 08:00:00","stars":1,"title":"第54期","type":"Girl","url":"http://gank.io/images/5888858f49bd4608b12633115687ddc3","views":331},{"_id":"5e8c80bb31ec89ebfc601f10","author":"鸢媛","category":"Girl","createdAt":"2020-04-12 08:00:00","desc":"你能看见山，看见海，看见这世上的一切，我目光短浅，只能看到你。","images":["http://gank.io/images/65c8ea426cc7423987692872968c25b9"],"likeCounts":0,"publishedAt":"2020-04-12 08:00:00","stars":1,"title":"第53期","type":"Girl","url":"http://gank.io/images/65c8ea426cc7423987692872968c25b9","views":227},{"_id":"5e8c80ae2bce50b3ceaa80f0","author":"鸢媛","category":"Girl","createdAt":"2020-04-11 08:00:00","desc":"我没那么坚强，只是习惯了什么事都自己扛。 \u200b\u200b\u200b\u200b","images":["http://gank.io/images/1c5cebd307fd49eaa75b368b11118b61"],"likeCounts":0,"publishedAt":"2020-04-11 08:00:00","stars":1,"title":"第52期","type":"Girl","url":"http://gank.io/images/1c5cebd307fd49eaa75b368b11118b61","views":259}]
     * page : 1
     * page_count : 7
     * status : 100
     * total_counts : 61
     */

    private int page;
    private int page_count;
    private int status;
    private int total_counts;
    private List<DataBean> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(int total_counts) {
        this.total_counts = total_counts;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 5e958f3b17bf93950887f20a
         * author : 鸢媛
         * category : Girl
         * createdAt : 2020-04-20 08:00:00
         * desc : 单身的时候好好经营自己，
         未来遇到那个值得你爱的人，才不会怯场。
         * images : ["http://gank.io/images/5a29ab0fc093408c82febe7c7e42e156"]
         * likeCounts : 0
         * publishedAt : 2020-04-20 08:00:00
         * stars : 1
         * title : 第61期
         * type : Girl
         * url : http://gank.io/images/5a29ab0fc093408c82febe7c7e42e156
         * views : 31
         */

        private String _id;
        private String author;
        private String category;
        private String createdAt;
        private String desc;
        private int likeCounts;
        private String publishedAt;
        private int stars;
        private String title;
        private String type;
        private String url;
        private int views;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
