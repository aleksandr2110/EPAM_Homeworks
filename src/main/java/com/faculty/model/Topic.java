package com.faculty.model;

import java.io.Serializable;

/**
 * Created by Aleksandr on 08.11.2020.
 */
public class Topic implements Serializable {

    private long topicId;
    private String topicName;

    public Topic(long topicId, String topicName)
    {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (topicId != topic.topicId) return false;
        return topicName.equals(topic.topicName);
    }

    @Override
    public int hashCode() {
        int result = (int) (topicId ^ (topicId >>> 32));
        result = 31 * result + topicName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
