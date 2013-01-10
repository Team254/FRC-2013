/*
 * ProfiledPIDSource
 */
package com.team254.lib.control;

import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Tom Bottiglieri
 */
public class ProfiledPIDSource implements PIDSource {

    PIDSource m_source;
    ProfiledPIDController m_controller;
    
    ProfiledPIDSource(PIDSource sensor, ProfiledPIDController controller) {
        m_source = sensor;
        m_controller = controller;
    }
    
    public double pidGet() {
        m_controller.calculate();
        return m_source.pidGet();
    }
    
    public double pidGetRaw() {
        return m_source.pidGet();
    }
}
