<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="webfileadmin">
        <title>Web File Admin | ${path?.name}</title>
    </head>
    <body>
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${path?.name}
                <small>${params.file ?: '/'}</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">${path?.name}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="alert alert-danger alert-dismissable" style="display:none">
                <i class="fa fa-check"></i>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <b>Failed!</b> <span></span>
            </div>
            <div class="alert alert-success alert-dismissable" style="display:none">
                <i class="fa fa-check"></i>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <b>Success!</b> <span></span>
            </div>

            <div class="box">
                <!-- form start -->
                <form role="form" id="fileForm" action="${request.contextPath}/wfadmin/${params.path}/save" >
                    <input type="hidden" name="file" value="${params.file}" />
                    <div class="box-body">
                        <div class="form-group">
                            <label for="filenameInput">File Name</label>
                            <input type="text" class="form-control" name="filename" id="filenameInput" placeholder="File Name" value="${file?.getName()}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Content</label>
                            <textarea class="form-control" name="content" rows="15" placeholder="Enter ...">${file?.text}</textarea>
                        </div>
                    </div><!-- /.box-body -->

                    <div class="box-footer">
                        <button type="button" class="btn btn-primary" onclick="saveFile()">Save</button>
                        <button type="button" class="btn btn-default" onclick="history.go(-1);">Back</button>
                    </div>
                </form>
            </div>

        </section><!-- /.content -->
    </body>
</html>
