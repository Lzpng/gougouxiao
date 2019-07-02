// 窗口加载完
window.onload = function () {
    // 创建Vue对象
    var vue = new Vue({
        el: '#app', // 元素绑定
        data: { // 数据模型
            dataList: [], // 定义数组，接收后台响应数据
            entity: {}, // 数据封装对象(表单)
            ids: [], // 复选框选中的id数组
            checked: false, // 全选复选框是否选中
            grade: 1, //分类级别
            itemCat1: {}, //用来存储2级分类
            itemCat2:{}, //用来存储3级分类
            // itemCat: [], //用来存储各级分类
            typeTemplateList: [], //类型模型
            parentId: 0 //绑定父级id
        },
        methods: { // 定义操作方法
            findItemCatParentId: function (parentId) { // 根据父级id
                // 发送异步请求
                axios.get("/itemCat/findItemCatParentId?parentId=" + parentId)
                    .then(function (response) {
                        // 获取响应数据
                        vue.dataList = response.data;
                        // 设置ids数组为空
                        vue.ids = [];
                        // 设置父级id
                        vue.parentId = parentId;
                    });
            },
            //查询下级
            selectList: function (entity, grade) {
                this.grade = grade;
                if (this.grade === 1) {
                    this.itemCat1 = {}; //一级分类
                    this.itemCat2 = {}; //二级分类
                }
                if (this.grade === 2) {
                    this.itemCat1 = entity; //一级分类
                    this.itemCat2 = {}; //二级分类
                }

                if (this.grade === 3) {
                    this.itemCat2 = entity; //二级分类

                }
                // console.log(entity.id);
                this.findItemCatParentId(entity.id);
            },
            //查询类型模板数据
            findTypeTemplateList:function () {
                axios.get("/typeTemplate/findTypeTemplateList").then(function (value) {
                    // console.log("aaa" + value.data);
                    vue.typeTemplateList = value.data;
                    //把第一个类型模板默认现实
                    vue.entity.typeId = vue.typeTemplateList[0].id;
                })
            },
            saveOrUpdate: function () { // 添加或修改
                var url = "save"; // 添加
                if (this.entity.id) {
                    url = "update"; // 修改
                }else{
                    //将父级id封装到entity对象中
                    this.entity.parentId = this.parentId;
                }
                // 发送异步请求
                axios.post("/itemCat/" + url, this.entity)
                    .then(function (response) {
                        // 获取响应数据
                        if (response.data) { // 操作成功
                            // 重新加载数据
                            vue.findItemCatParentId(vue.parentId);
                        } else {
                            alert('操作失败！');
                        }
                    });
            },
            show: function (entity) { // 数据回显
                // 把entity对象转化成json字符串
                var jsonStr = JSON.stringify(entity);
                // 把json字符串转化成一个新的json对象
                this.entity = JSON.parse(jsonStr);
            },
            checkAll: function (e) { // 全选复选框
                this.ids = []; // 先清空数组
                if (e.target.checked) { // 判断复选框是否选中
                    for (var i = 0; i < this.dataList.length; i++) {
                        this.ids.push(this.dataList[i].id);
                    }
                }
            },
            del: function () { // 删除
                if (this.ids.length > 0) {
                    axios.get("/itemCat/delete?ids="
                        + this.ids).then(function (response) {
                        if (response.data) {
                            // 重新加载数据
                            vue.findItemCatParentId(vue.parentId);
                        } else {
                            alert("删除失败！");
                        }
                    });
                } else {
                    alert("请选择要删除的记录！");
                }
            }
        },
        created: function () { // 创建生命周期(初始化方法)
            // 根据父级id查询商品分类
            this.findItemCatParentId(0);
            // 查询类型模板
            this.findTypeTemplateList();
        },
        updated: function () { // 更新数据生命周期
            // 检查全选checkbox是否选中
            this.checked = (this.ids.length == this.dataList.length);
        }
    });
};