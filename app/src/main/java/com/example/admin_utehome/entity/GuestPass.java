package com.example.admin_utehome.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "guest_passes",
        indices = {
                @Index(value = {"code"}, unique = true)
        }
)
public class GuestPass {

    public GuestPass() {}
    @PrimaryKey(autoGenerate = true)
    public int id;

    /** Mã căn hộ */
    public int apartmentId;

    /** Mã QR (unique, dùng để scan) */
    public String code;

    /** Salt để tăng bảo mật khi generate code */
    public String salt;

    /** Thời điểm tạo (timestamp để dễ so sánh) */
    public long createdAt;

    /** Thời gian bắt đầu hiệu lực */
    public long validFrom;

    /** Thời gian hết hạn */
    public long validTo;

    /** ACTIVE | EXPIRED | CANCELLED */
    public String status;

}