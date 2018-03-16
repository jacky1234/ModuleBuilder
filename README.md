# ModuleBuilder


## NicePlayer
　　炫酷的音频播放lib，开发之中

## 使用
#### 1. 在application#onCreate中
```
NicePlayer.get().init(this);
```

#### 2. 配置参数
 ```
  NicePlayer.get()
         .start(new ParamsCreator
                 .Builder()
                 .load("http://filebag-1252817547.cosgz.myqcloud.com/201710/4bc3fbdd-9732-469d-afba-0a7ea651be61.mp3")
                 .build()
         );
 ```







