import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ClimateDateMain{
	public static void main(String[] args) throws Exception {
    if (args.length != 2) {
        System.err.println("Usage: ClimateDate <input path> <output path>");
        System.exit(-1);
      }
      
      Configuration conf = new Configuration();
  	Job job = Job.getInstance(conf, "ClimateDate");
  	job.setJar("ClimateDate.jar");


      FileInputFormat.addInputPath(job, new Path(args[0]));
      FileOutputFormat.setOutputPath(job, new Path(args[1]));
      
      job.setMapperClass(ClimateDateMapper.class);
      job.setReducerClass(ClimateDateReducer.class);

      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(DoubleWritable.class);
      
      System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
  }
