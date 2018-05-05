package lylace;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// MaxTemp abc xyz
// args[0] = abc
// args[1] = xyz

public class MaxTemp {

	public static void main(String[] args) throws Exception {

		// 맵리듀스 작업 객체 정의
		Job job = new Job();
		job.setJarByClass(MaxTemp.class);
		job.setJobName("Max Temperature");

		// 맵리듀스 작업에 필요한 입출력 파일 지정
		FileInputFormat.addInputPath(job, new Path(args[0]));

		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 맵리듀스 작업 시 사용할 Mapper/Reducer 지정
		job.setMapperClass(MaxTempMapper.class);
		job.setReducerClass(MaxTempReducer.class);

		// 맵리듀스 작업 시 키/값 유형 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// 작업 종료 - 맵리듀스 실행 결과에 따름
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
