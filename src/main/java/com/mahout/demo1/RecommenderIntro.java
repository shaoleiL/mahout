package com.mahout.demo1;



import com.google.common.base.Preconditions;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

/**
 * 基于用户的mahout推荐程序
 * Created by shaolei on 2015/11/11.
 */
public class RecommenderIntro {
    public static void main(String[] args) throws Exception {
        //装在数据文件
        DataModel mode = new FileDataModel(new File(("F:\\mahout\\intro.csv")));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(mode);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, mode);
        //生成推荐引擎
        Recommender recommender = new GenericUserBasedRecommender(mode, neighborhood, similarity);
        //为用户1推荐一件商品
        List<RecommendedItem> recommendations = recommender.recommend(1, 1);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }




    }
}
