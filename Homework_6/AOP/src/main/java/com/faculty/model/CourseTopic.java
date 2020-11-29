package com.faculty.model;

import java.io.Serializable;

/**
 * Created by Aleksandr on 20.09.2020.
 */
public class CourseTopic implements Serializable {

    private long courseTopicId;
    private long courseId;
    private long topicId;

    public CourseTopic(long courseId, long topicId)
    {
        this.courseId = courseId;
        this.topicId = topicId;
    }

    public CourseTopic(long courseTopicId, long courseId, long topicId)
    {
        this.courseTopicId = courseTopicId;
        this.courseId = courseId;
        this.topicId = topicId;
    }

    public long getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(long courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseTopic that = (CourseTopic) o;

        if (courseTopicId != that.courseTopicId) return false;
        if (courseId != that.courseId) return false;
        return topicId == that.topicId;
    }

    @Override
    public int hashCode() {
        int result = (int) (courseTopicId ^ (courseTopicId >>> 32));
        result = 31 * result + (int) (courseId ^ (courseId >>> 32));
        result = 31 * result + (int) (topicId ^ (topicId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CourseTopic{" +
                "courseTopicId=" + courseTopicId +
                ", courseId=" + courseId +
                ", topicId=" + topicId +
                '}';
    }
}
