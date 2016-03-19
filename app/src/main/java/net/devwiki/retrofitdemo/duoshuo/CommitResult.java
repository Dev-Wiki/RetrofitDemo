package net.devwiki.retrofitdemo.duoshuo;

/**
 * Created by zyz on 2016/3/19.
 */
public class CommitResult {

    /**
     * code : 0
     * response : {"post_id":"1152985276292923003","parent_id":"1152985276292923000","thread_id":"1152923703638301959","status":"approved","source":"duoshuo","author_key":"1","author_name":"Perchouli","author_url":"http://weibo.com/perchouli","message":"登陆多说发表评论","created_at":"2012-11-01T11:36:38+08:00","likes":0,"reports":0,"comments":0,"reposts":0,"type":"","privileges":{"delete":true,"update":true,"moderate":true},"author":{"user_id":"2288","name":"Perchouli","url":"http://weibo.com/perchouli","avatar_url":"http://tp3.sinaimg.cn/1893629610/50/1292295250/1","domain":"","connected_services":{"weibo":"1893629610","douban":"3382783","baidu":"839143886"}}}
     */

    private int code;
    /**
     * post_id : 1152985276292923003
     * parent_id : 1152985276292923000
     * thread_id : 1152923703638301959
     * status : approved
     * source : duoshuo
     * author_key : 1
     * author_name : Perchouli
     * author_url : http://weibo.com/perchouli
     * message : 登陆多说发表评论
     * created_at : 2012-11-01T11:36:38+08:00
     * likes : 0
     * reports : 0
     * comments : 0
     * reposts : 0
     * type :
     * privileges : {"delete":true,"update":true,"moderate":true}
     * author : {"user_id":"2288","name":"Perchouli","url":"http://weibo.com/perchouli","avatar_url":"http://tp3.sinaimg.cn/1893629610/50/1292295250/1","domain":"","connected_services":{"weibo":"1893629610","douban":"3382783","baidu":"839143886"}}
     */

    private ResponseEntity response;

    public void setCode(int code) {
        this.code = code;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public ResponseEntity getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "CommitResult{" +
                "code=" + code +
                ", response=" + response +
                '}';
    }

    public static class ResponseEntity {
        private String post_id;
        private String parent_id;
        private String thread_id;
        private String status;
        private String source;
        private String author_key;
        private String author_name;
        private String author_url;
        private String message;
        private String created_at;
        private int likes;
        private int reports;
        private int comments;
        private int reposts;
        private String type;
        /**
         * delete : true
         * update : true
         * moderate : true
         */

        private PrivilegesEntity privileges;
        /**
         * user_id : 2288
         * name : Perchouli
         * url : http://weibo.com/perchouli
         * avatar_url : http://tp3.sinaimg.cn/1893629610/50/1292295250/1
         * domain :
         * connected_services : {"weibo":"1893629610","douban":"3382783","baidu":"839143886"}
         */

        private AuthorEntity author;

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public void setThread_id(String thread_id) {
            this.thread_id = thread_id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setAuthor_key(String author_key) {
            this.author_key = author_key;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public void setAuthor_url(String author_url) {
            this.author_url = author_url;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public void setReports(int reports) {
            this.reports = reports;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public void setReposts(int reposts) {
            this.reposts = reposts;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setPrivileges(PrivilegesEntity privileges) {
            this.privileges = privileges;
        }

        public void setAuthor(AuthorEntity author) {
            this.author = author;
        }

        public String getPost_id() {
            return post_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public String getThread_id() {
            return thread_id;
        }

        public String getStatus() {
            return status;
        }

        public String getSource() {
            return source;
        }

        public String getAuthor_key() {
            return author_key;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public String getAuthor_url() {
            return author_url;
        }

        public String getMessage() {
            return message;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getLikes() {
            return likes;
        }

        public int getReports() {
            return reports;
        }

        public int getComments() {
            return comments;
        }

        public int getReposts() {
            return reposts;
        }

        public String getType() {
            return type;
        }

        public PrivilegesEntity getPrivileges() {
            return privileges;
        }

        public AuthorEntity getAuthor() {
            return author;
        }

        @Override
        public String toString() {
            return "ResponseEntity{" +
                    "post_id='" + post_id + '\'' +
                    ", parent_id='" + parent_id + '\'' +
                    ", thread_id='" + thread_id + '\'' +
                    ", status='" + status + '\'' +
                    ", source='" + source + '\'' +
                    ", author_key='" + author_key + '\'' +
                    ", author_name='" + author_name + '\'' +
                    ", author_url='" + author_url + '\'' +
                    ", message='" + message + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", likes=" + likes +
                    ", reports=" + reports +
                    ", comments=" + comments +
                    ", reposts=" + reposts +
                    ", type='" + type + '\'' +
                    ", privileges=" + privileges +
                    ", author=" + author +
                    '}';
        }

        public static class PrivilegesEntity {
            private boolean delete;
            private boolean update;
            private boolean moderate;

            public void setDelete(boolean delete) {
                this.delete = delete;
            }

            public void setUpdate(boolean update) {
                this.update = update;
            }

            public void setModerate(boolean moderate) {
                this.moderate = moderate;
            }

            public boolean isDelete() {
                return delete;
            }

            public boolean isUpdate() {
                return update;
            }

            public boolean isModerate() {
                return moderate;
            }

            @Override
            public String toString() {
                return "PrivilegesEntity{" +
                        "delete=" + delete +
                        ", update=" + update +
                        ", moderate=" + moderate +
                        '}';
            }
        }

        public static class AuthorEntity {
            private String user_id;
            private String name;
            private String url;
            private String avatar_url;
            private String domain;
            /**
             * weibo : 1893629610
             * douban : 3382783
             * baidu : 839143886
             */

            private ConnectedServicesEntity connected_services;

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public void setConnected_services(ConnectedServicesEntity connected_services) {
                this.connected_services = connected_services;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getName() {
                return name;
            }

            public String getUrl() {
                return url;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public String getDomain() {
                return domain;
            }

            public ConnectedServicesEntity getConnected_services() {
                return connected_services;
            }

            @Override
            public String toString() {
                return "AuthorEntity{" +
                        "user_id='" + user_id + '\'' +
                        ", name='" + name + '\'' +
                        ", url='" + url + '\'' +
                        ", avatar_url='" + avatar_url + '\'' +
                        ", domain='" + domain + '\'' +
                        ", connected_services=" + connected_services +
                        '}';
            }

            public static class ConnectedServicesEntity {
                private String weibo;
                private String douban;
                private String baidu;

                public void setWeibo(String weibo) {
                    this.weibo = weibo;
                }

                public void setDouban(String douban) {
                    this.douban = douban;
                }

                public void setBaidu(String baidu) {
                    this.baidu = baidu;
                }

                public String getWeibo() {
                    return weibo;
                }

                public String getDouban() {
                    return douban;
                }

                public String getBaidu() {
                    return baidu;
                }

                @Override
                public String toString() {
                    return "ConnectedServicesEntity{" +
                            "weibo='" + weibo + '\'' +
                            ", douban='" + douban + '\'' +
                            ", baidu='" + baidu + '\'' +
                            '}';
                }
            }
        }
    }
}
