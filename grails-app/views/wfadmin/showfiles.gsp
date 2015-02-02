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
            
            <g:if test="${error}">
            <div class="callout callout-danger">
                <h4>${error}</h4>
            </div>
            </g:if>

            <g:else>
                <div class="box box-primary">
                    <div class="box-header">
                        <h3 class="box-title">File List</h3>
                        <div class="box-tools pull-right">
                            <button class="btn btn-sm btn-default" data-toggle="modal" data-target="#newFolderModal">New Folder</button>
                            <button class="btn btn-sm btn-default" onclick="newFile('${request.contextPath}/wfadmin/${params.path}','${params.file}');">New File</button>
                            <button class="btn btn-sm btn-default" data-toggle="modal" data-target="#uploadModal">Upload</button>
                        </div>
                    </div>
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-striped">
                            <tbody><tr>
                                <th style="width: 10px">#</th>
                                <th>Name</th>
                                <th>Path</th>
                                <th style="width: 140px">Control</th>
                            </tr>
                            <g:each var="f" in="${files}" status="st">
                            <g:set var="fpath" value="${f.getAbsolutePath().replace(root+path.path,'')}"/>
                            <tr>
                                <td>${st+1}.</td>
                                <td><a href="${request.contextPath}/wfadmin/${params.path}?file=${fpath}">${f.getName()}</a></td>
                                <td>${fpath}</td>
                                <td>
                                    <a class="btn btn-danger btn-sm" onclick="$(this).parent().find('a').toggle();">Delete</a>
                                    <a class="btn btn-default btn-sm" style="display:none;" onclick="$(this).parent().find('a').toggle();">Cancel</a>
                                    <a class="btn btn-danger btn-sm" style="display:none;" onclick="$(this).parent().find('a').toggle();deleteFile('${request.contextPath}/wfadmin/${params.path}','${fpath}')">Confirm</a>
                                </td>
                            </tr>
                            </g:each>
                        </tbody></table>
                    </div><!-- /.box-body -->
                </div>

                <!-- Modal -->
                <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                        <form role="form" action="${request.contextPath}/wfadmin/${params.path}/upload" enctype="multipart/form-data" method="POST">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">File Upload</h4>
                          </div>
                          <div class="modal-body">
                            <div class="form-group">
                                <label for="inputFile">Please select a file:</label>
                                <input type="hidden" name="file" value="${params.file}">
                                <input type="file" id="inputFile" name="uploadfile">
                                <p class="help-block">eg. css/js/jpg</p>
                            </div>
                          </div>
                          <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          </div>
                        </form>
                    </div>
                  </div>
                </div>

                <div class="modal fade" id="newFolderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                        <form role="form" action="${request.contextPath}/wfadmin/${params.path}/newfolder" method="POST">
                          <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">New Folder</h4>
                          </div>
                          <div class="modal-body">
                            <div class="form-group">
                                <label for="inputDirname">Please input folder's name: </label>
                                <input type="hidden" name="file" value="${params.file}">
                                <input type="text" id="inputDirname" name="dirname">
                            </div>
                          </div>
                          <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          </div>
                        </form>
                    </div>
                  </div>
                </div>
            </g:else>
            
        </section><!-- /.content -->
    </body>
</html>
