<template>
    <div>
        <p>
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
                            <button class="btn btn-xs btn-danger">
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
              chapters:[]
          }
        },
        mounted:function() { //初始化加载
            let _this = this;
            _this.$refs.pagination.size = 5;
            _this.list(1);
            //siderbar激活样式方法一
            // this.$parent.activeSidebar("business-chapter-sidebar");

        },
        methods:{
            save(){
                let _this = this;
                _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/save",_this.chapter).then((response)=>{
                    console.log("保存大章结果：",response);
                    var resp = response.data;
                    if (resp.success){
                        $("#form-modal").modal("hide");
                        _this.list(1);
                    }
                })
            },
            edit(chapter){
                let _this = this;
                _this.chapter = $.extend({},chapter);
                $("#form-modal").modal("show");

            },
            add(){
                let _this = this;
                _this.chapter = {};
                $("#form-modal").modal("show");
            },
            list(page){
                let _this = this;
                _this.$ajax.post("http://127.0.0.1:9000/business/admin/chapter/list",{
                    page:page,
                    size:_this.$refs.pagination.size
                }).then((response)=>{
                    console.log("大章列表："+response);
                    var resp = response.data;
                    _this.chapters = resp.content.list;
                    _this.$refs.pagination.render(page,resp.content.total);
                })
            }
        }
    }
</script>