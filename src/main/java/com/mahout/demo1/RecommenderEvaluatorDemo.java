package com.mahout.demo1;

import com.google.common.base.Preconditions;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by shaolei on 2015/11/26 13:44.
 */
public class RecommenderEvaluatorDemo {
    public static void main(String[] args) throws IOException, TasteException {
        RandomUtils.useTestSeed();  //生成可重复的结果
        final DataModel model = new FileDataModel(new File(("F:\\mahout\\intro.csv")));
        //AverageAbsoluteDifferenceRecommenderEvaluator 计算用户的预测值与实际值之间的平均绝对差
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder builder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity,model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };
        double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
        System.out.println(score);
    }
}
