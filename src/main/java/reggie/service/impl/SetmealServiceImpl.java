package reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reggie.entity.Setmeal;
import reggie.mapper.SetmeaMapper;
import reggie.service.SetmealService;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmeaMapper, Setmeal> implements SetmealService {
}
