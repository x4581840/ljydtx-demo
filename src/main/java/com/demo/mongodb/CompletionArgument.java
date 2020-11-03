package com.demo.mongodb;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * jianyong.long
 */
public class CompletionArgument {
    @NotNull(message = "sessionTracking id cannot be null")
    private Long sessionUserTrackingId;
    @NotNull(message = "assessment Id cannot be null")
    private Long assessmentId;
    @NotNull(message = "session Id cannot be null")
    private Long sessionId;
    @NotNull(message = "student Id cannot be null")
    private Integer studentId;
    @NotNull
    private Integer attemptNo;
    @NotNull
    private Integer assessmentType; //1=同步，2=模拟

    public Long getSessionUserTrackingId() {
        return sessionUserTrackingId;
    }

    public CompletionArgument setSessionUserTrackingId(final Long sessionUserTrackingId) {
        this.sessionUserTrackingId = sessionUserTrackingId;
        return this;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public CompletionArgument setAssessmentId(final Long assessmentId) {
        this.assessmentId = assessmentId;
        return this;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public CompletionArgument setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public Integer getAttemptNo() {
        return attemptNo;
    }

    public CompletionArgument setAttemptNo(Integer attemptNo) {
        this.attemptNo = attemptNo;
        return this;
    }

    public Integer getAssessmentType() {
        return assessmentType;
    }

    public CompletionArgument setAssessmentType(Integer assessmentType) {
        this.assessmentType = assessmentType;
        return this;
    }
}
