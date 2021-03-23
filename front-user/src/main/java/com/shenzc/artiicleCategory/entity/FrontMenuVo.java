package com.shenzc.artiicleCategory.entity;

import com.shenzc.entity.front.FrontMenu;
import lombok.Data;

import java.util.List;

@Data
public class FrontMenuVo extends FrontMenu {

    private List<FrontMenu> childFrontMenu;

}
