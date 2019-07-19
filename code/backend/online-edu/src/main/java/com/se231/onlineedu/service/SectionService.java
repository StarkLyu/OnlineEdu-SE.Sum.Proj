package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.Section;

/**
 * Section Service Interface
 *
 * interface of service related to section
 *
 * @author Zhe Li
 * @date 2019/07/19
 */
public interface SectionService {
    Section createSection(Long courseId, TitleAndDes form);
}
