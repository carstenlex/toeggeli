:: INT
oc delete all -l app=toeggeliservice -n rch-toeggeli-int --force=true
oc create -f imageStream.yaml -n rch-toeggeli-int
oc create -f bc.yaml -n rch-toeggeli-int
oc start-build toeggeliservice -n rch-toeggeli-int --wait
oc create -f dc.yaml -n rch-toeggeli-int
oc rollout latest toeggeliservice -n rch-toeggeli-int
oc create -f service.yaml -n rch-toeggeli-int
oc create -f route.yaml -n rch-toeggeli-int
