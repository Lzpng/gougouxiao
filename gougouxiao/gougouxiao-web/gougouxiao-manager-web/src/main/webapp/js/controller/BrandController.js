//页面加载完后再响应
window.onload = function () {
    //创建vue对象
    var vue = new Vue({
        el: '#app',//元素绑定
        data: { //数据类型
            dataList: [], //定义数据，用来响应后台
            entity: {}, //用来封装对象（表单）
            pages: 100, //设置总页数
            page: 1,   //当前页码
            searchEntity: {}, //搜索条件的数据封装
            ids: [], //用来接收复选框点击的id
            checked: false //全选的复选框是否选中
        },
        methods: {//定义操作方法
            search: function (page) {//搜索方法
                // 发送异步请求
                // alert('aaa');
                //{params: this.searchEntity}如果有传递值的话，会自动拼接
                axios.get("/brand/findByPage?page=" + page, {params: this.searchEntity}).then(function (response) {
                    //获取响应数据
                    vue.dataList = response.data.rows;// response.data应该是{pages:20,rows:[{},{}]}
                    //设置总页数
                    vue.pages = response.data.pages;
                    //将传进来的页码赋值给模型
                    //设置当前页码
                    vue.page = page;
                    //当点击下一页的时候，上一页的选中的数据将被清空
                    vue.ids = [];
                });
            },
            saveOrUpdate: function () { //添加、修改方法
                var url = "save";
                if (this.entity.id) {//如果id存在
                    url = "update";
                }
                axios.post("/brand/" + url, this.entity).then(function (response) {
                    if (response.data) {//操作成功
                        vue.search(vue.page);//重新加载页面
                    } else {
                        alert("操作失败")
                    }
                })
            },
            show: function (entity) {//回显数据
                // 把entity对象转化成json字符串,如果不转成json字符串的话(将Obj转成json字符串)
                var jsonStr = JSON.stringify(entity);
                // alert(jsonStr);
                //把json字符串转化成一个新的json对象
                this.entity = JSON.parse(jsonStr);
                // console.log(entity)
            },
            checkAll: function (e) {//全选框
                //每次点击的时候清除ids数组
                vue.ids = [];
                //得到dom对象中的checked属性，如果选中就为true，不选中就为false
                // alert(e.target.checked);
                if (e.target.checked) {//判断是否选中
                    for (var i = 0; i < vue.dataList.length; i++) {
                        //将一页数据的id都选上
                        vue.ids.push(vue.dataList[i].id);
                    }
                }
            },
            //删除品牌
            del: function () {
                if (this.ids.length > 0) {
                    axios.get("/brand/delete?ids=" + this.ids).then(function (value) {
                        if (value.data) {
                            //如果page已经没有数据了，page就直接等于1
                            if (vue.page < 0) {
                                vue.page = 1;
                            }
                            // 计算当前页码(如果删除为整页查询上一页)
                            var page = vue.checked ? vue.page - 1 : vue.page;
                            // 重新加载数据
                            vue.search(page);
                        } else {
                            alert("操作失败")
                        }
                    })
                } else {
                    alert("请选择要删除的品牌")
                }
            }
        },
        created:
            function () { // 创建生命周期(初始化方法)
                //调用搜索方法
                this.search(this.page);
            },
        updated: function () {//更新数据生命周期
            //检查全选checkbox是否选中
            this.checked = this.ids.length == this.dataList.length;
        }
    });
}