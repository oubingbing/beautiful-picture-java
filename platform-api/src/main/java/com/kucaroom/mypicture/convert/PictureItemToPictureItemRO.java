package com.kucaroom.mypicture.convert;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.responseObject.PictureItemRO;import com.kucaroom.mypicture.responseObject.PictureRO;import org.springframework.beans.BeanUtils;import java.util.List;import java.util.stream.Collectors;public class PictureItemToPictureItemRO {    /**     * 转化     *     * @author yezi     * @param pictureItem     * @return     */    public static PictureItemRO convert(PictureItem pictureItem){        PictureItemRO pictureItemRO = new PictureItemRO();        BeanUtils.copyProperties(pictureItem,pictureItemRO);        pictureItemRO.setPictureId(pictureItem.getPictureId());        return  pictureItemRO;    }    /**     * 循环转化     *     * @author yezi     * @param pictureItemList     * @return     */    public static List<PictureItemRO> convert(List<PictureItem> pictureItemList){        return pictureItemList.stream().map(                e -> convert(e)        ).collect(Collectors.toList());    }}