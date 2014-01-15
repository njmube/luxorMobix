import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.luxsoft.sec.Role;
import com.luxsoft.sec.User;
import com.luxsoft.sec.UserRole;

class BootStrap {

    def init = { servletContext ->
		def adminRole=Role.findOrSaveWhere(authority:'ROLE_ADMIN')
		def userRole=Role.findOrSaveWhere(authority:'ROLE_USER')
		
		def admin=User.findByUsername('admin')
		if(!admin){
			admin=new User(username:'admin',password:'admin').save(flush:true)
			UserRole.create(admin,userRole,true)
			UserRole.create(admin,adminRole,true)
		}
		def guest=User.findByUsername('guest')
		if(!guest){
			guest=new User(username:'guest',password:'password').save(flush:true)
			UserRole.create(guest,userRole,true)
		}
		java.security.Security.addProvider(new BouncyCastleProvider())
    }
    def destroy = {
    }
}
