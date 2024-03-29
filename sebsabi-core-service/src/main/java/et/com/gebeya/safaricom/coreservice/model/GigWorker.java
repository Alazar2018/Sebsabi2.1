package et.com.gebeya.safaricom.coreservice.model;

import et.com.gebeya.safaricom.coreservice.dto.GigWorkerRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gigworkers")
public class GigWorker extends Person{
    private String qualification;
    private int Age;
    private Date dob;
    @OneToOne
    @JoinColumn(name = "assigned_form_id") // This assumes the column name in your GigWorker table
    private Form assignedForm;
    public GigWorker(GigWorkerRequest gigWorkerRequest){
        this.setFirstName(gigWorkerRequest.getFirstName());
        this.setLastName(gigWorkerRequest.getLastName());
        this.setEmail(gigWorkerRequest.getEmail());
        this.setPassword(gigWorkerRequest.getPassword());
        this.setQualification(gigWorkerRequest.getQualification());
        this.setDob(gigWorkerRequest.getDob());
        this.setAge(gigWorkerRequest.getAge());
        this.setIsActive(gigWorkerRequest.getIsActive());
    }

}
