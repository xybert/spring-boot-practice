package com.xybert.springbootupload.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description 文件类型枚举
 * @author xybert
 * @date 2022/10/26 14:20
 */

@Getter
public enum FileTypeEnum {
    /** JPEG  (jpg)*/
    JPEG("jpg", "FFD8FF"),

    /** PNG */
    PNG("png", "89504E47"),

    /** GIF */
    GIF("gif", "47494638"),

    /** TIFF (tif)  */
    TIFF("tif", "49492A00"),

    /** Windows bitmap (bmp) */
    BMP("bmp","424D"),

    /** 16色位图(bmp) */
    BMP_16("bmp","424D228C010000000000"),

    /** 24色位图(bmp) */
    BMP_24("bmp","424D8240090000000000"),

    /** 256色位图(bmp) */
    BMP_256("bmp","424D8E1B030000000000"),

    /** CAD  (dwg) */
    DWG("dwg", "41433130"),

    /** Adobe photoshop  (psd)*/
    PSD("psd", "38425053"),

    /** Rich Text Format  (rtf)*/
    RTF("rtf", "7B5C727466"),

    /** XML */
    XML("xml", "3C3F786D6C"),

    /** HTML (html)*/
    HTML("html", "68746D6C3E"),

    /** Email [thorough only] (eml)*/
    EML("eml", "44656C69766572792D646174653A"),

    /** Outlook Express (dbx) */
    DBX("dbx", "CFAD12FEC5FD746F "),

    /** Outlook (pst)*/
    PST("pst", "2142444E"),

    /** doc;xls;dot;ppt;xla;ppa;pps;pot;msi;sdw;db */
    OLE2("ole2", "0xD0CF11E0A1B11AE1"),

    /** Microsoft Word/Excel 注意：word 和 excel的文件头一样 */
    XLS("xls", "D0CF11E0"),

    /** Microsoft Word/Excel 注意：word 和 excel的文件头一样 */
    DOC("doc", "D0CF11E0"),

    /** Microsoft Word/Excel 2007以上版本文件 注意：word 和 excel的文件头一样 */
    DOCX("docx", "504B0304"),

    /** Microsoft Word/Excel 2007以上版本文件 注意：word 和 excel的文件头一样 504B030414000600080000002100*/
    XLSX("xlsx", "504B0304"),

    /** Microsoft Access (mdb)*/
    MDB("mdb", "5374616E64617264204A"),

    /** Word Perfect (wpd)*/
    WPD("wpd", "FF575043"),

    /** Postscript */
    EPS("eps", "252150532D41646F6265"),

    /** Postscript */
    PS("ps", "252150532D41646F6265"),

    /** Adobe Acrobat (pdf)  */
    PDF("pdf", "255044462D312E"),

    /** Quicken (qdf) */
    QDF("qdf", "AC9EBD8F"),

    /** QuickBooks Backup (qdb) */
    QDB("qbb", "458600000600"),

    /** Windows Password  (pwl)*/
    PWL("pwl", "E3828596"),

    /** ZIP Archive */
    ZIP("zip", "504B0304"),

    /** ARAR Archive */
    RAR("rar", "52617221"),

    /** WAVE (wav) */
    WAV("wav", "57415645"),

    /** AVI */
    AVI("avi", "41564920"),

    /** Real Audio (ram)*/
    RAM("ram", "2E7261FD"),

    /** Real Media (rm) rmvb/rm相同  */
    RM("rm", "2E524D46"),

    /** Real Media (rm) rmvb/rm相同  */
    RMVB("rmvb", "2E524D46000000120001"),

    /** MPEG (mpg)  */
    MPG("mpg", "000001BA"),

    /** Quicktime  (mov)*/
    MOV("mov", "6D6F6F76"),

    /** Windows Media (asf) */
    ASF("asf", "3026B2758E66CF11"),

    /** ARJ Archive */
    ARJ("arj", "60EA"),

    /** MIDI (mid) */
    MID("mid", "4D546864"),

    /** MP4 */
    MP4("mp4", "00000020667479706D70"),

    /** MP3 */
    MP3("mp3", "49443303000000002176"),

    /** FLV */
    FLV("flv", "464C5601050000000900"),

    /** 1F8B0800000000000000 */
    GZ("gz", "1F8B08"),

    /** CSS */
    CSS("css", "48544D4C207B0D0A0942"),

    /**  JS */
    JS("js", "696B2E71623D696B2E71"),

    /**  Visio */
    VSD("vsd", "d0cf11e0a1b11ae10000"),

    /** WPS文字wps、表格et、演示dps都是一样的 */
    WPS("wps", "d0cf11e0a1b11ae10000"),

    /** torrent */
    TORRENT("torrent", "6431303A637265617465"),

    /** JSP Archive */
    JSP("jsp", "3C2540207061676520"),

    /** JAVA Archive */
    JAVA("java", "7061636B61676520"),

    /** CLASS Archive */
    CLASS("class", "CAFEBABE0000002E00"),

    /** JAR Archive */
    JAR("jar", "504B03040A000000"),

    /** MF Archive */
    MF("mf", "4D616E69666573742D56"),

    /** EXE Archive */
    EXE("exe", "4D5A9000030000000400"),

    /** ELF Executable */
    ELF("elf", "7F454C4601010100"),

    /** Lotus 123 v1 */
    WK1("wk1", "2000604060"),

    /** Lotus 123 v3 */
    WK3("wk3", "00001A0000100400"),

    /** Lotus 123 v5 */
    WK4("wk4", "00001A0002100400"),

    /** Lotus WordPro v9 */
    LWP("lwp", "576F726450726F"),

    /** Sage(sly.or.srt.or.slt;sly;srt;slt) */
    SLY("sly", "53520100"),

    NOT_EXITS_ENUM("", ""),
    ;

    /**
     * 文件类型对应的名称
     */
    final String fileTypeName;

    /**
     * 文件类型对应的魔术数字
     */
    final String magicNumberCode;

    FileTypeEnum(String fileTypeName, String magicNumberCode) {
        this.fileTypeName = fileTypeName;
        this.magicNumberCode = magicNumberCode;
    }

    /**
     * 根据文件类型获取文件类型魔数编码
     *
     * @param magicNumberCode - 文件类型魔数编码
     * @return FileTypeEnum
     */
    public static FileTypeEnum getByMagicNumberCode(String magicNumberCode) {
        if (StringUtils.isNotBlank(magicNumberCode)) {
            for (FileTypeEnum type : values()) {
                if (magicNumberCode.toUpperCase().startsWith(type.getMagicNumberCode())) {
                    return type;
                }
            }
        }

        return FileTypeEnum.NOT_EXITS_ENUM;
    }

    /**
     * 根据文件类型后缀名获取枚举
     *
     * @param fileTypeName - 文件类型后缀名
     * @return FileTypeEnum
     */
    public static FileTypeEnum getByFileTypeName(String fileTypeName) {
        if (StringUtils.isNotBlank(fileTypeName)) {
            for (FileTypeEnum type : values()) {
                if (type.getFileTypeName().equals(fileTypeName)) {
                    return type;
                }
            }
        }
        return FileTypeEnum.NOT_EXITS_ENUM;
    }
}