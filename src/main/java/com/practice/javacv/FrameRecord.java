package com.practice.javacv;
 
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.*;
 
/**
 * @author ：created by mmzsblog.cn
 * @description：javaCV开发详解之3：收流器实现，录制流媒体服务器的rtsp/rtmp视频文件(基于javaCV-FFMPEG)
 * @date ：created at 2020/07/07 10:57
 */
@Slf4j
public class FrameRecord {
//    public static void main(String[] args)
//            throws FrameRecorder.Exception, FrameGrabber.Exception, InterruptedException {
//
////        String inputFile = "rtsp://admin:admin@192.168.0.236:1935/cam/realmonitor?channel=1&subtype=0";
////        String inputFile = "rtmp://192.168.0.236:1935/live/1256";
//        String inputFile = "rtmp://192.168.0.14:1935/live/888";
//
//        // Decodes-encodes
//        String outputFile = "d:/recorde.mp4";
//        try {
//            FrameRecord frame = new FrameRecord();
//            frame.frameRecord(inputFile, outputFile, 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 
 
    /**
     * 按帧录制视频
     *
     * description: 如果获取到的视频流有卡顿? 则要分析视频源，如果视频源本身没有卡顿的话，可以结合视频源的刷新率，考虑提高grabber的帧率，同时提高recorder的帧率。如果视频源本身就有卡顿话，那就需要另外想办法解决视频源卡顿的问题。
     * @param inputFile   -该地址可以是网络直播/录播地址，也可以是远程/本地文件路径
     * @param outputFile  -该地址只能是文件地址，如果使用该方法推送流媒体服务器会报错，原因是没有设置编码格式
     * @throws FrameGrabber.Exception
     * @throws FrameRecorder.Exception
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     */
    public void frameRecord(String inputFile, String outputFile, int audioChannel)
            throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        FFmpegLogCallback.set();
 
        boolean isStart = true;//该变量建议设置为全局控制变量，用于控制录制结束
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        // 获取流设置帧率参数
//        grabber.setOption("rtsp_transport", "tcp");
//        grabber.setOption("rtmp_transport", "tcp");
//        grabber.setTimeout(300);
//        grabber.setOption("stimeout", "2000000");
//        // 设置读取的最大数据，单位字节
//        grabber.setOption("probesize", "10000");
//        // 设置分析的最长时间，单位微秒
//        grabber.setOption("analyzeduration", "10000");

//        grabber.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//        grabber.setFrameRate(30);
//        grabber.setVideoBitrate(3000000);
//        grabber.setFormat("mp4");


 
        // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 1280, 720, audioChannel);
        // 输出流设置帧率参数
//        recorder.setFrameRate(30);
//        recorder.setVideoBitrate(3000000);
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//        recorder.setFormat("mp4");
        recorder.start();
        // 停止取视频源
//        stopRecordByFrame(isStart);
        // 开始取视频源
        recordByFrame(grabber, recorder, isStart);
    }
 
    /**
     * 保存视频源到本地
     * @param grabber
     * @param recorder
     * @param status
     * @throws Exception
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     */
    private static void recordByFrame(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder, Boolean status) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception {
        try {
            // 用来标记控制
            int i = -2999;

            //建议在线程中使用该方法
            grabber.start();
//            recorder.start();
            Frame frame = null;
            while (status && (frame = grabber.grabFrame()) != null) {
                recorder.record(frame);
//                System.out.println(i++);
                // 停止录制,此处方式过于简陋；读者可自行修改，比如线程控制之类的
                if (i++ > 2999) {
                    status = false;
                }
 
            }
            recorder.stop();
            grabber.stop();
        } finally {
            if (grabber != null) {
                grabber.stop();
            }
        }
    }
}
