package consul

import java.net.Inet4Address

import consul.v1.acl.AclRequests
import consul.v1.agent.AgentRequests
import consul.v1.catalog.CatalogRequests
import consul.v1.common.Types
import consul.v1.event.EventRequests
import consul.v1.health.HealthRequests
import consul.v1.kv.KvRequests
import consul.v1.session.SessionRequests
import consul.v1.status.StatusRequests

import scala.concurrent.ExecutionContext

trait ConsulApiV1{
  def acl:     AclRequests
  def agent:   AgentRequests
  def catalog: CatalogRequests
  def event:   EventRequests
  def health:  HealthRequests
  def kv:      KvRequests
  def session: SessionRequests
  def status:  StatusRequests

}

class Consul(address: Inet4Address, port: Int = 8500)(implicit executionContext: ExecutionContext){

  lazy val v1: ConsulApiV1 with Types = new ConsulApiV1 with Types{
    private lazy val basePath = s"http://${address.getHostAddress}:$port/v1"
    lazy val health:  HealthRequests  = HealthRequests( basePath)
    lazy val agent:   AgentRequests   = AgentRequests(  basePath)
    lazy val catalog: CatalogRequests = CatalogRequests(basePath)
    lazy val kv:      KvRequests      = KvRequests(     basePath)
    lazy val status:  StatusRequests  = StatusRequests( basePath)
    lazy val acl:     AclRequests     = AclRequests(    basePath)
    lazy val session: SessionRequests = SessionRequests(basePath)
    lazy val event:   EventRequests   = EventRequests(  basePath)
  }

}
