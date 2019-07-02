// 注册组件
Vue.component('v-select', VueSelect.VueSelect);
// 窗口加载完
window.onload = function () {
    // 创建Vue对象
    var vue = new Vue({
        el: '#app', // 元素绑定
        data: { // 数据模型
            dataList: [], // 定义数组，接收后台响应数据
            entity: {brandIds: [], specIds: [], customAttributeItems: []}, // 数据封装对象(表单)
            page: 1, // 当前页码
            pages: 0, // 总页数
            searchEntity: {}, // 搜索条件数据封装
            ids: [], // 复选框选中的id数组
            checked: false, // 全选复选框是否选中
            brandList: [], // 品牌数组{id: 1, text: '华为'}, {id: 2, text: '小米'}, {id: 3, text: '红米'}
            specList: [] //规格数据{id: 1, text: '手机'}, {id: 2, text: '网络'}, {id: 3, text: '尺码'}
        },
        methods: { // 定义操作方法
            search: function (page) { // 搜索方法
                // 发送异步请求
                axios.get("/typeTemplate/findByPage?page=" + page,
                    {params: this.searchEntity})
                    .then(function (response) {
                        // 获取响应数据
                        vue.dataList = response.data.rows;
                        // 设置总页数
                        vue.pages = response.data.pages;
                        // 设置当前页码
                        vue.page = page;
                        // 设置ids数组为空
                        vue.ids = [];
                    });
            },
            saveOrUpdate: function () { // 添加或修改
                var url = "save"; // 添加
                if (this.entity.id) {
                    url = "update"; // 修改
                }
                // 发送异步请求
                axios.post("/typeTemplate/" + url, this.entity)
                    .then(function (response) {
                        // 获取响应数据
                        if (response.data) { // 操作成功
                            // 重新加载数据
                            vue.search(vue.page);
                        } else {
                            alert('操作失败！');
                        }
                    });
            },
            show: function (entity) { // 数据回显
                // alert(entity);
                // 把entity对象转化成json字符串
                var jsonStr = JSON.stringify(entity);

                // 把json字符串转化成一个新的json对象
                this.entity = JSON.parse(jsonStr);

                //将品牌json字符串转换成json对象数组将{id:'',text:''}直接转成object
                this.entity.brandIds = JSON.parse(this.entity.brandIds);

                //将规格json字符串转成json数组
                this.entity.specIds = JSON.parse(this.entity.specIds);
                //将扩展属性转成json数组
                this.entity.customAttributeItems = JSON.parse(this.entity.customAttributeItems);
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
                    axios.get("/typeTemplate/delete?ids="
                        + this.ids).then(function (response) {
                        if (response.data) {
                            // 计算当前页码(如果删除为最后一页查询上一页)
                            var page = vue.page == vue.pages && vue.checked
                                ? vue.page - 1 : vue.page;
                            // 重新加载数据
                            vue.search(page);
                        } else {
                            alert("删除失败！");
                        }
                    });
                } else {
                    alert("请选择要删除的记录！");
                }
            },
            // 查询品牌数据
            findBrandList: function () {
                axios.get("/brand/findBrandList").then(function (value) {
                    vue.brandList = value.data;
                })
            },
            //查询规格数据
            findSpecList: function () {
                axios.get("/specification/findSpecList").then(function (value) {
                    vue.specList = value.data;
                });
            },
            //添加行
            addTableRows: function () {
                this.entity.customAttributeItems.push({});
            },
            //删除行
            delTableRows: function (idx) {
                this.entity.customAttributeItems.splice(idx, 1);
            },
            // 提取数组中json某个属性，返回拼接的字符串(逗号分隔)
            jsonArr2Str: function (jsonArrStr) {
                // console.log("aaaa" + jsonArrStr);
                //把json字符串解析成一个对象数组
                var jsonArr = JSON.parse(jsonArrStr);

                // console.log("bbbb" + jsonArr);
                //用来拼接字符串的数组
                var resArr = [];
                for (var i = 0; i < jsonArr.length; i++) {
                    resArr.push(jsonArr[i].text);
                }
                //将数组中的每个元素以,分开
                return resArr.join(",");
            }
        },
        created: function () { // 创建生命周期(初始化方法)
            // 调用搜索方法
            this.search(this.page);
            // 查询品牌数据
            this.findBrandList();
            //查询规格数据
            this.findSpecList();
        },
        updated: function () { // 更新数据生命周期
            // 检查全选checkbox是否选中
            this.checked = (this.ids.length == this.dataList.length);
        }
    });
};