# PhotoDialog
实现一行代码添加照片弹窗

在 build.gradle的repositories 中 添加如下代码:
maven { url 'https://www.jitpack.io' }

在调用模块的gradle的repositories中添加如下代码：
implementation 'com.github.SunStarJ:PhotoDialog:0.1.1'

基础使用时使用下方代码

PhotoSelectDialog selectDialog = new PhotoSelectDialog(this, imgPath);//实例化

selectDialog.show(new PhotoSelectDialog.PhotoSelectCheckListener() {
                    
                    @Override
                    public void check() {
                        //查看图片代码
                    }

                    @Override
                    public void formCamera() {
                        //拍照代码
                    }

                    @Override
                    public void formBox() {
                        //从相册选择代码
                    }
                });
