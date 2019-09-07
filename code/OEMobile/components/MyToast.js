import {Toast} from "native-base";

const defaultOptions = {
    duration: 3000,
    buttonText: 'OK',
};

const showToast = (text, type, customOptions) => {
    Toast.show({
        text,
        type,
        ...customOptions
    });
};

export default {
    showToast,
    errorToast: (errorText, customOptions = defaultOptions) => {
        showToast(errorText, "danger", customOptions);
    },
    successToast: (successText, customOptions = defaultOptions) => {
        showToast(successText, "success", customOptions);
    },
    infoToast: (infoText, customOptions = defaultOptions) => {
        showToast(infoText, "info", customOptions);
    }
};
