<template>
    <div>
        <h3>{{course.name}}</h3>
        <p>
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                返回课程
            </router-link>
            &nbsp;
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>

        </p>
        <pagination ref="pagination" v-bind:list="list" ></pagination>
        <div>
            <table id="simple-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>课程ID</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <tr v-for="chapter in chapters">
                    <td>{{chapter.id}}</td>
                    <td>{{chapter.name}}</td>
                    <td>{{chapter.courseId}}</td>
                    <td>
                        <div class="  btn-group">
                            <button v-on:click="edit(chapter)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>
                            <button v-on:click="del(chapter.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>
                        </div>
                    </td>
                </tr>

                </tbody>
            </table>

            <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">表单</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" >
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <input v-model="chapter.name" class="form-control" placeholder="名称">
                                    </div>
                                </div><div class="form-group">
                                    <label class="col-sm-2 control-label">课程ID</label>
                                    <div class="col-sm-10">
                                        <input v-model="chapter.courseId" class="form-control" placeholder="课程ID">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" v-on:click="save()"  class="btn btn-primary">保存</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </div>
    </div>

</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        components: {Pagination},
        comments:{Pagination},
        name: 'chapter',
        data: function(){
          return{
              chapter:{},
              chapters:[],
              course:{}
          }
        },
        mounted:function() { //初始化加载
            let _this = this;
            _this.$refs.pagination.size = 5;
            let course = SessionStorage.get("course") ||{};
            if(Tool.isEmpty(course)){
                _this.$router.push("/welcome");
            }
            _this.course = course;
            _this.list(1);
            //siderbar激活样式方法一
            // this.$parent.activeSidebar("business-chapter-sidebar");

        },
        methods:{
            /*
             *  删除
             */
            del(id){
                let _this = this;

                Confirm.show("删除大章后不可恢复，确认删除？",function (result) {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER+"/business/admin/chapter/delete/"+id,_this.chapter).then((response)=>{
                        Loading.hide();
                        var resp = response.data;
                        if (resp.success){
                            _this.list(1);
                            Toast.success("删除成功！");
                        }
                    })
                })
            },
            /*
             *  保存
             */
            save(){
                let _this = this;

                if(!Validator.require(_this.chapter.name,"姓名")||
                    !Validator.require(_this.chapter.courseId,"课程ID")||
                    !Validator.length(_this.chapter.courseId,"课程ID",1,8)){
                    return;
                }


                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/chapter/save",_this.chapter).then((response)=>{
                    Loading.hide();
                    var resp = response.data;
                    if (resp.success){
                        $("#form-modal").modal("hide");
                        _this.list(1);
                    }else{
                        Toast.warning(resp.message);
                    }
                })
            },

            /*
             *  编辑按钮
             */
            edit(chapter){
                let _this = this;
                _this.chapter = $.extend({},chapter);
                $("#form-modal").modal("show");

            },
            /*
             *  新增按钮
             */
            add(){
                let _this = this;
                _this.chapter = {};
                $("#form-modal").modal("show");
            },
            /*
             *  查询（刷新按钮）
             */
            list(page){
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/chapter/list",{
                    page:page,
                    size:_this.$refs.pagination.size
                }).then((response)=>{
                    Loading.hide()
                    var resp = response.data;
                    _this.chapters = resp.content.list;
                    _this.$refs.pagination.render(page,resp.content.total);
                })
            }
        }
    }
</script>