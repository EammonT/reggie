package reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reggie.common.CustomException;
import reggie.entity.Category;
import reggie.entity.Dish;
import reggie.entity.Setmeal;
import reggie.mapper.CategoryMapper;
import reggie.service.CategoryService;
import reggie.service.DishService;
import reggie.service.SetmealService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据Id删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类是否关联菜品
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(queryWrapper);
        if (count>0){
            //已经关联菜品，抛出业务异常
            throw new CustomException("当前分类关联菜品，不能删除！");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2>0){
            //已经关联套餐，抛出业务异常
            throw new CustomException("当前分类关联套餐，不能删除！");
        }
        //正常删除
        super.removeById(id);
    }
}
