package com.mf.upload;

import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class UploadServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //判断上传的表单是普通表单还是带文件的表单
       if (!ServletFileUpload.isMultipartContent(req)) return;
       //创建保存路径，建议在web-inf目录下安全用户无法直接访问
        String upLoadRealPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File upLoadFile = new File(upLoadRealPath);
        if(!upLoadFile.exists()) upLoadFile.mkdir();
        //缓存，临时文件
        //加入文件超过预期大小我们将他放临时文件中过几天自动删除会提醒用户转永
        //创建保存路径，建议在web-inf目录下安全用户无法直接访问
        String tempRealPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tempFile = new File(tempRealPath);
        if(!tempFile.exists()) tempFile.mkdir();

        //1.创建DiskFileItemFactory对象，处理文件上传路径或者掉限制：
        DiskFileItemFactory factory = getDiskFileItemFactory(tempFile);
        //2.获取servletFileUpload
        ServletFileUpload upload = getServletUpload(factory);
        //3.处理上传的文件
        String msg= upLoadParseRequest(upload,req,upLoadRealPath);
        //servlet请求转发消息
        req.setAttribute("msg",msg);
        req.getRequestDispatcher("info.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置缓冲区，当文件大于缓冲区的时候将他放到临时文件
        factory.setSizeThreshold(1024*1024);//1M
        factory.setRepository(file);
        return factory;
    }
    public static ServletFileUpload getServletUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long l, long l1, int i) {
//                System.out.println("总大小："+l1+" 已上传："+l);
            }
        });
        //处理乱码
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件最大值
        upload.setFileSizeMax(1024*1024*10);
        //设置总共能够上传文件的大小
        upload.setSizeMax(1024*1024*10);
        return upload;
    }
    public static String  upLoadParseRequest(ServletFileUpload upload,HttpServletRequest req,String upLoadRealPath) throws FileUploadException, IOException {
        //将前端请求解析封装成一个FileItem对象,需要从servletUpload获取
        List<FileItem> fileItems = upload.parseRequest(req);
        String msg=null;
        for (FileItem fileItem : fileItems) {
            //判断上传的文件是普通的表单还是带文件的表单
            if (fileItem.isFormField()){
                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                System.out.println(fieldName + ": " + value);
            }else {
                //==============处理文件=======================
                String upLoadFieldName = fileItem.getName();
                System.out.println("upLoadFieldName=>"+upLoadFieldName);
                //文件名合法？
                if (upLoadFieldName==null||upLoadFieldName.trim().equals("")) continue;
                //获取文件名和后缀民
                String fileName = upLoadFieldName.substring(upLoadFieldName.lastIndexOf("/") + 1);
                String fileExtName = upLoadFieldName.substring(upLoadFieldName.lastIndexOf(".") + 1);
                /*网络传输中东西都需要序列化，POJO实体类要在多个电脑运行也需要继承序列化标记接口*/
                //UUID.randomUUID()生成唯一通用码
                String uuidPath = UUID.randomUUID().toString();

                //==============存放地址=======================
                String realPath = upLoadRealPath + "/" + uuidPath;
                //给每个文件创建一个文件夹
                File realFilePath = new File(realPath);
                if (!realFilePath.exists()) realFilePath.mkdir();

                //==============文件传输=======================
                InputStream in = fileItem.getInputStream();
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);
                byte[] buffer = new byte[1024 * 1024];
                int len=0;
                while ((len=in.read(buffer))>0){
                    fos.write(buffer,0,len);
                }
                fos.close();
                in.close();

                msg="上传成功";
                fileItem.delete();//清除临时文件
            }
        }
        return msg;
    }
}
