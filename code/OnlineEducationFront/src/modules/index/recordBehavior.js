class RecordBehavior {
    recordBehavior = (action, info) => {
        return {
            action,
            time: new Date(),
            info
        }
    };
    recordVideoBehavior = (action, videoName, videoTime, extraInfo) => {
        let videoInfo = {
            videoName,
            videoTime
        };
        Object.assign(videoInfo, extraInfo);
        return this.recordBehavior(action, videoInfo);
    };
    recordStartPlay = (videoName, videoTime) => {
        return this.recordVideoBehavior("START_PLAY", videoName, videoTime)
    };
    recordPause = (videoName, videoTime) => {
        return this.recordVideoBehavior("PAUSE", videoName, videoTime);
    };
    recordContinue = (videoName, videoTime) => {
        return this.recordVideoBehavior("CONTINUE", videoName, videoTime);
    };
    recordChangeSpeed = (videoName, videoTime, newSpeed) => {
        return this.recordVideoBehavior("CHANGE_SPEED", videoName, videoTime, {
            newSpeed
        });
    }
}

export default RecordBehavior;
